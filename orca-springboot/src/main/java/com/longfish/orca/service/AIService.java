package com.longfish.orca.service;

import java.io.InputStream;
import java.util.List;

public interface AIService {

    String createSession();

    List<String> listSession();

    InputStream send(String text);
}
