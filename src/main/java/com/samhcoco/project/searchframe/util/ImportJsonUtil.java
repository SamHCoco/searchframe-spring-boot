package com.samhcoco.project.searchframe.util;


import com.google.gson.Gson;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.io.FileUtils.readFileToString;

public abstract class ImportJsonUtil {

    public static final Gson gson = new Gson();

    public static <T> List<T> importJson(Resource resource, Class<T[]> className) throws IOException {
        return Arrays.asList(gson.fromJson(readFileToString(resource.getFile(), UTF_8), className));
    }

}
