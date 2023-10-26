package kopo.poly.controller;


import kopo.poly.dto.MsgDTO;
import kopo.poly.dto.NoticeDTO;
import kopo.poly.dto.OcrDTO;
import kopo.poly.service.IOcrService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.DateUtil;
import kopo.poly.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/ocr")
@Controller
public class OcrController {

    private final IOcrService ocrService;

    final private String FILE_UPLOAD_SAVE_PATH = "c:/upload";

    final private String FILE_DOWNLOAD_SAVE_PATH = "c:/download";


    @GetMapping(value = "uploadImage")
    public String uploadImage() {
        log.info(this.getClass().getName() + ".uploadImage!");

        return "ocr/uploadImage";
    }

    @GetMapping(value = "ocrList")
    public String OcrList(ModelMap model) throws Exception {

        log.info(this.getClass().getName() + ".OcrList Start!");

        List<OcrDTO> rList = Optional.ofNullable(ocrService.getOcrList())
                .orElseGet(ArrayList::new);


        // 조회된 리스트 결과값 넣어주기
        model.addAttribute("rList", rList);

        log.info(this.getClass().getName() + ".OcrList End!");

        return "ocr/ocrList";
    }

    @ResponseBody
    @PostMapping(value = "ocrDownload")
    public void fileDownload(HttpServletRequest request) throws IOException {
        log.info(this.getClass().getName() + ".fileDownload!");

        try {
            String filename = CmmUtil.nvl(request.getParameter("filename"));
            String filepath = CmmUtil.nvl(request.getParameter("filepath"));
            String ext = CmmUtil.nvl(request.getParameter("ext")).toUpperCase();
            String saveFilePath = FileUtil.mkdirForDate(FILE_DOWNLOAD_SAVE_PATH);
            String full = filepath.trim() + "/" + filename.trim();
            String full2 = full.replaceAll("/", "\\\\\\\\");
            log.info(full2);

            String savefull = saveFilePath + "/" + filename.trim();
            String savefull2 = savefull.replaceAll("/", "\\\\\\\\");
            log.info(savefull2);

            File file = new File(filename);
            BufferedImage image = ImageIO.read(new File(full2));
            if(image != null) {
                log.info("1");
            }
            if(file.exists()) {
                log.info("2");
            }
            ImageIO.write(image, ext, file);
            log.info(this.getClass().getName() + ".readImage End!");
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @PostMapping(value = "readImage")
    public String readImage(ModelMap model, @RequestParam(value = "fileUpload") MultipartFile mf) throws Exception {
        log.info(this.getClass().getName() + ".readImageStart!");

        String res = "";

        String originalFileName = mf.getOriginalFilename();

        log.info(originalFileName);

        String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1,
                originalFileName.length()).toLowerCase();

        log.info(ext);

        if (ext.equals("jpeg") || ext.equals("jpg") || ext.equals("gif") || ext.equals("png")) {

            String saveFileName = DateUtil.getDateTime("HHmmss") + "." + ext;

            String saveFilePath = FileUtil.mkdirForDate(FILE_UPLOAD_SAVE_PATH);

            String fullFileInfo = saveFilePath + "/" + saveFileName;

            log.info("ext : " + ext);
            log.info("saveFileName : " + saveFileName);
            log.info("saveFilePath : " + saveFilePath);
            log.info("fullFileInfo : " + fullFileInfo);

            mf.transferTo(new File(fullFileInfo));

            OcrDTO pDTO = new OcrDTO();

            pDTO.setFileName(saveFileName);
            pDTO.setFilePath(saveFilePath);
            pDTO.setExt(ext);
            pDTO.setOrgFileName(originalFileName);
            pDTO.setRegId("admin");
            pDTO.setChgId("admin");

            OcrDTO rDTO = Optional.ofNullable(ocrService.getReadforImageText(pDTO)).orElseGet(OcrDTO::new);

            res = CmmUtil.nvl(rDTO.getTextFormImage());

            rDTO = null;
            pDTO = null;

        } else {
            res = "이미지 파일이 아니라서 인식이 불가능합니다.";
        }
        model.addAttribute("res", res);
        log.info(this.getClass().getName() + ".readImage End!");

        return "ocr/readImage";
    }

}
