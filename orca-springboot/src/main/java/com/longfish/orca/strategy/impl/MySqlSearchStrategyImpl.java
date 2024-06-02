package com.longfish.orca.strategy.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.longfish.orca.context.BaseContext;
import com.longfish.orca.mapper.DocumentMapper;
import com.longfish.orca.pojo.entity.Document;
import com.longfish.orca.pojo.vo.DocumentSearchVO;
import com.longfish.orca.strategy.SearchStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.longfish.orca.constant.CommonConstant.POST_TAG;
import static com.longfish.orca.constant.CommonConstant.PRE_TAG;

@Service("mySqlSearchStrategyImpl")
public class MySqlSearchStrategyImpl implements SearchStrategy {

    @Autowired
    private DocumentMapper documentMapper;

    private String tempKeywords;

    //TODO 改进搜索策略 -> 根据 index 标记 \ 提取展示文本长度变量
    @Override
    public List<DocumentSearchVO> searchDocument(String keywords) {
        if (StringUtils.isBlank(keywords)) {
            return new ArrayList<>();
        }
        this.tempKeywords = markdownToPlainText(keywords).trim();
        List<Document> documents = documentMapper.selectList(new LambdaQueryWrapper<Document>()
                .eq(Document::getIsDeleted, 0)
                .eq(Document::getUserId, BaseContext.getCurrentId())
                .and(i -> i.like(Document::getTitle, this.tempKeywords)
                        .or()
                        .like(Document::getContent, this.tempKeywords)
                        .or()
                        .like(Document::getDocAbstract, this.tempKeywords)));
        return documents.stream().map(item -> {
            item.setContent(markdownToPlainText(item.getContent()));
            boolean isLowerCase = true;
            String content;
            int contentIndex = item.getContent().indexOf(this.tempKeywords.toLowerCase());
            if (contentIndex == -1) {
                contentIndex = item.getContent().indexOf(this.tempKeywords.toUpperCase());
                if (contentIndex != -1) {
                    isLowerCase = false;
                }
            }
            if (contentIndex != -1) {
                int preIndex = contentIndex > 15 ? contentIndex - 15 : 0;
                String preText = item.getContent().substring(preIndex, contentIndex);
                int last = contentIndex + this.tempKeywords.length();
                int postLength = item.getContent().length() - last;
                int postIndex = postLength > 35 ? last + 35 : last + postLength;
                String postText = item.getContent().substring(contentIndex, postIndex);
                if (isLowerCase) {
                    content = (preText + postText).replaceAll(this.tempKeywords.toLowerCase(), PRE_TAG + this.tempKeywords.toLowerCase() + POST_TAG);
                } else {
                    content = (preText + postText).replaceAll(this.tempKeywords.toUpperCase(), PRE_TAG + this.tempKeywords.toUpperCase() + POST_TAG);
                }
            } else {
                if (item.getContent().length() > 30) {
                    content = item.getContent().substring(0, 31);
                } else content = item.getContent();
            }
            isLowerCase = true;
            String docAbstract;
            int abstractIndex = item.getDocAbstract().indexOf(this.tempKeywords.toLowerCase());
            if (abstractIndex == -1) {
                abstractIndex = item.getDocAbstract().indexOf(this.tempKeywords.toUpperCase());
                if (abstractIndex != -1) {
                    isLowerCase = false;
                }
            }
            if (abstractIndex != -1) {
                int preIndex = abstractIndex > 15 ? abstractIndex - 15 : 0;
                String preText = item.getDocAbstract().substring(preIndex, abstractIndex);
                int last = abstractIndex + this.tempKeywords.length();
                int postLength = item.getDocAbstract().length() - last;
                int postIndex = postLength > 35 ? last + 35 : last + postLength;
                String postText = item.getDocAbstract().substring(abstractIndex, postIndex);
                if (isLowerCase) {
                    docAbstract = (preText + postText).replaceAll(this.tempKeywords.toLowerCase(), PRE_TAG + this.tempKeywords.toLowerCase() + POST_TAG);
                } else {
                    docAbstract = (preText + postText).replaceAll(this.tempKeywords.toUpperCase(), PRE_TAG + this.tempKeywords.toUpperCase() + POST_TAG);
                }
            } else {
                if (item.getDocAbstract().length() > 30) {
                    docAbstract = item.getDocAbstract().substring(0, 31);
                } else docAbstract = item.getDocAbstract();

            }
            isLowerCase = true;
            int titleIndex = item.getTitle().indexOf(this.tempKeywords.toLowerCase());
            if (titleIndex == -1) {
                titleIndex = item.getTitle().indexOf(this.tempKeywords.toUpperCase());
                if (titleIndex != -1) {
                    isLowerCase = false;
                }
            }
            String docTitle;
            if (isLowerCase) {
                docTitle = item.getTitle().replaceAll(this.tempKeywords.toLowerCase(), PRE_TAG + this.tempKeywords.toLowerCase() + POST_TAG);
            } else {
                docTitle = item.getTitle().replaceAll(this.tempKeywords.toUpperCase(), PRE_TAG + this.tempKeywords.toUpperCase() + POST_TAG);
            }
            return DocumentSearchVO.builder()
                    .id(item.getId())
                    .title(docTitle)
                    .content(content)
                    .docAbstract(docAbstract)
                    .build();
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    private String markdownToPlainText(String input) {
        if (input == null) return null;
        input = input.replaceAll("<[^>]*>", "");
        input = input.replaceAll("\n", "");
        input = input.replaceAll("#", "");
        input = input.replaceAll("`", "");
        input = input.replaceAll("~", "");
        input = input.replaceAll("\\*", "");
        input = input.replaceAll("-", "");
        input = input.replaceAll("\\s+", " ");
        input = input.replaceAll("!\\[(.*?)]\\([^)]*\\)", "$1");
        input = input.replaceAll("\\[(.*?)]\\([^)]*\\)", "$1");
        input = input.replaceAll("\\[", "");
        input = input.replaceAll(">", "");
        return input;
    }

}
