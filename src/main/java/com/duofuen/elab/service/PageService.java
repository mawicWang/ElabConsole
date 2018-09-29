package com.duofuen.elab.service;

import com.duofuen.elab.domain.Page;
import com.duofuen.elab.domain.PageRepository;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.zip.ZipEntry;

@Service
public class PageService {
    private final PageRepository pageRepository;

    @Autowired
    public PageService(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }


    public Iterable<Page> findAll() {
        return pageRepository.findAll();
    }

    public Page findById(Integer id) {
        if (id != null) {
            Optional<Page> page = pageRepository.findById(id);
            if (page.isPresent()) {
                return page.get();
            }
        }

        return new Page();
    }

    public void save(Page page) {
        pageRepository.save(page);
    }

    public boolean deleteById(Integer id) {
        try {
            pageRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param file
     * @return 0 - success, 1 - 解压失败, -1 - 未知
     */
    public int uploadFile(MultipartFile file,String md5) {
        int SUCCESS = 0;
        int UNZIP_FAIL = 1;
        int UNKNOWN_FAIL = -1;

        InputStream fis = null;

        String destDir = "/var/www/html/h5/" + md5 + "/";
        File dir = new File(destDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            fis = file.getInputStream();
            byte[] buffer = new byte[1024];
            ZipArchiveInputStream zis = new ZipArchiveInputStream(fis);
            ZipEntry ze = zis.getNextZipEntry();
            while (ze != null) {
                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);
                if (ze.getName().endsWith(File.separator)) {
                    System.out.println("Creating Folder " + newFile.getAbsolutePath());
                    newFile.mkdirs();
                } else {
                    System.out.println("Unzipping to " + newFile.getAbsolutePath());
                    //create directories for sub directories in zip
                    FileOutputStream fos = new FileOutputStream(newFile);
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                    //close this ZipEntry
                    //            zis.closeEntry();
                }
                ze = zis.getNextZipEntry();
            }
            //close last ZipEntry
//            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            return UNZIP_FAIL;
        } catch (Exception e) {
            e.printStackTrace();
            return UNKNOWN_FAIL;
        }

        return SUCCESS;
    }

    public boolean deleteFile(String path) {
        try {
            FileUtils.deleteDirectory(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
