package com.longfish.orca.strategy.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.longfish.orca.context.BaseContext;
import com.longfish.orca.mapper.DocumentMapper;
import com.longfish.orca.pojo.entity.Document;
import com.longfish.orca.pojo.vo.DocumentSearchVO;
import com.longfish.orca.properties.SearchDisplayLengthProperties;
import com.longfish.orca.strategy.SearchStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.longfish.orca.constant.CommonConstant.POST_TAG;
import static com.longfish.orca.constant.CommonConstant.PRE_TAG;

@Service("mySqlSearchStrategyImpl")
public class MySqlSearchStrategyImpl implements SearchStrategy {

    @Autowired
    private DocumentMapper documentMapper;

    @Autowired
    private SearchDisplayLengthProperties lengthProperties;

    private String tempKeywords;

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
                        .like(Document::getContent, this.tempKeywords)));
        List<DocumentSearchVO> ret = new ArrayList<>();
        documents.forEach(item -> {
            String docTitle = item.getTitle();
            String content = markdownToPlainText(item.getContent());

            List<Integer> titleIndexes = findIndexes(docTitle, this.tempKeywords);
            List<Integer> contentIndexes = findIndexes(content, this.tempKeywords);

            contentIndexes.forEach(i -> {
                int preIndex = Math.max(i - lengthProperties.getPreLength(), 0);
                int postIndex = Math.min(i + this.tempKeywords.length() + lengthProperties.getPostLength(), content.length());
                String voContent = new StringBuilder(content.substring(preIndex, postIndex))
                        .insert(i - preIndex + this.tempKeywords.length(), POST_TAG)
                        .insert(i - preIndex, PRE_TAG).toString();
                ret.add(DocumentSearchVO.builder()
                        .id(item.getId())
                        .title(highlightKeywords(docTitle, this.tempKeywords))
                        .content(voContent)
                        .build());
            });
            if (contentIndexes.size() == 0 && titleIndexes.size() != 0) {
                ret.add(DocumentSearchVO.builder()
                        .id(item.getId())
                        .title(highlightKeywords(docTitle, this.tempKeywords))
                        .content(content.substring(0,
                                Math.min(lengthProperties.getPreLength() + lengthProperties.getPostLength(), content.length())))
                        .build());
            }
        });
        return ret;
    }

    private List<Integer> findIndexes(String text, String keyword) {
        List<Integer> indexes = new ArrayList<>();
        if (StringUtils.isBlank(text) || StringUtils.isBlank(keyword)) {
            return indexes;
        }
        Matcher matcher = Pattern.compile("(?i)(" + Pattern.quote(keyword) + ")").matcher(text);
        while (matcher.find()) {
            indexes.add(matcher.start());
        }
        return indexes;
    }

    private String highlightKeywords(String text, String keyword) {
        if (StringUtils.isBlank(text) || StringUtils.isBlank(keyword)) {
            return text;
        }
        String regex = "(?i)(" + Pattern.quote(keyword) + ")";
        return text.replaceAll(regex, PRE_TAG + "$1" + POST_TAG);
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
