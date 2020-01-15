package com.derveljun.textmanager;

import com.derveljun.textmanager.utils.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@Controller
public class PageController {

    @Value("${derveljun.default.path}")
    private String defaultPath;

    @GetMapping({"/", "/index"})
    public String index(Model model) throws IOException {

        List<String> files = FileUtils.readFiles(defaultPath);
        files.sort(Comparator.naturalOrder());

        model.addAttribute("texts", files);
        return "index";
    }

    @GetMapping({"/manage"})
    public String manage(Model model) {
        model.addAttribute("title", "");
        model.addAttribute("contentText", "");
        return "manage";
    }

    @GetMapping({"/manage/{title}"})
    public String manage(@PathVariable String title, Model model) throws IOException {
        model.addAttribute("title", title);
        model.addAttribute("contentText", FileUtils.readTextFile(defaultPath, title));
        return "manage";
    }

}
