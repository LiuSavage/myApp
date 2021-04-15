package com.cbs.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 写真アップロード，消去のサービス
 */
@Service
@Transactional
public class FileUploadService {
	@Value("${preread.uploadPath}")
    private String resourceDir;
	/**
	 * 写真アップロードパス
	 * @param files 写真
	 * @param status ステータス
	 * @return 写真パス
	 */
	public String FilesUpload(MultipartFile[] files, String status) {

		Date date = new Date();
		String dataForm = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String reValue = "";
		if (files != null && files.length > 0) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				if(file.getOriginalFilename().equals("")) {
					continue;
				}else {
					String imageName = saveFile(file, status, sb);
					if (reValue.equals("")) {
						reValue += dataForm + "/" + imageName;
					} else {
						reValue += "," + dataForm + "/" + imageName;
					}
				}

			}
			return reValue;
		}
		return null;
	}

	/**
	 * 写真アップロード機能
	 * @param file　写真
	 * @param path　写真パス
	 * @param stringBuilder　写真名
	 * @return　写真名
	 */
	public String saveFile(MultipartFile file, String status, StringBuilder stringBuilder) {
		if (!file.isEmpty()) {
			String filePath = "";
			String storingFilePath = "";
			String stockFilePath = "";
			filePath=resourceDir;

			Date date = new Date();
			String dataForm = new SimpleDateFormat("yyyy-MM-dd").format(date);

			storingFilePath = filePath + "storing/" + dataForm + "/";
			stockFilePath = filePath + "stock/" + dataForm + "/";
			
			String fileName = file.getOriginalFilename();
			String suffixName = fileName.substring(fileName.lastIndexOf("."));
			fileName = UUID.randomUUID() + suffixName;
			File storingDest = new File(storingFilePath + fileName);
			if (!storingDest.getParentFile().exists()) {
				storingDest.getParentFile().mkdirs();
			}
			try {
				file.transferTo(storingDest);
				if(status.equals("stock")) {
					File stocDest = new File(stockFilePath);
					if (!stocDest.getParentFile().exists()) {
						stocDest.getParentFile().mkdirs();
					}
					FileUtils.copyFileToDirectory(storingDest, stocDest);
				}
				stringBuilder.append(storingFilePath + fileName).append(",");
				return fileName;
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 入庫写真コーヒー
	 * @param fileName　写真名
	 */
	public void storingFileCopy(String fileName) {
		String filePath = "";
		String storingFilePath = "";
		String stockFilePath = "";
		filePath=resourceDir;
		Date date = new Date();
		String dataForm = new SimpleDateFormat("yyyy-MM-dd").format(date);
		storingFilePath = filePath + "storing/";
		stockFilePath = filePath + "stock/" + dataForm + "/";
		
		File storingDest = new File(storingFilePath + fileName);
		File stocDest = new File(stockFilePath);
		if (!stocDest.getParentFile().exists()) {
			stocDest.getParentFile().mkdirs();
		}
		try {
			FileUtils.copyFileToDirectory(storingDest, stocDest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 写真コーヒー
	 * @param fileName　写真名
	 */
	public String fileCopy(String fileName) {
		String filePath=resourceDir;
		Date date = new Date();
		String dataForm = new SimpleDateFormat("yyyy-MM-dd").format(date);
		
        File source = new File(filePath + "stock/" + fileName);
        
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
		String newFileName = UUID.randomUUID() + suffixName;
		File target = new File(filePath + "stock/" +dataForm + "/" + newFileName);
		
		if (!target.getParentFile().exists()) {
			target.getParentFile().mkdirs();
		}
		try {
			FileUtils.copyFile(source, target);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return dataForm + "/" + newFileName;
        
		
	}

	/**
	 * 写真消除
	 * @param status ステータス
	 * @param delFile　写真パス
	 */
	public void deleteFile(String[] delFile, String status) {
		for(int i = 0; i < delFile.length; i++) {
			String path = resourceDir;
			if(status.equals("storing")) {
				path = path + "storing/";
			}else if(status.equals("stock")){
				path = path + "stock/";
			}
			File file = new File(path + delFile[i]);
			if (file.isFile() && file.exists()) {
				file.delete();
			}
		}
	}
}
