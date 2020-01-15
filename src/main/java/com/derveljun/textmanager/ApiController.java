package com.derveljun.textmanager;

import com.derveljun.textmanager.utils.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Value("${derveljun.default.path}")
    private String defaultPath;

    @PostMapping("/texts")
    public boolean saveTexts(@RequestBody TextVO vo) {

        FileUtils.writeTextFile(defaultPath, vo.getTitle(), vo.getContentText());

        return true;
    }

}
