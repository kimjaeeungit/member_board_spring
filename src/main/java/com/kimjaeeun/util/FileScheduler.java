package com.kimjaeeun.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.kimjaeeun.service.BoardServiceImpl;
import com.kimjaeeun.vo.Attach;

public class FileScheduler {
	public static void main(String[] args) {
		File file= new File("c:\\upload\\210913");
		File[] files = file.listFiles();
		for(File f : files) {
			System.out.println(f);
		}
		
		System.out.println("========================");
		
		List<Attach> dbList = new BoardServiceImpl().readAttachByPath("210913");
		for(Attach a : dbList) {
			System.out.println(a.getUuid());
		}
		
		System.out.println("========================");
		
		//배열 -> 리스트 변환
		List<File> fileList =new ArrayList<>(Arrays.asList(files));
		List<File> existFile = new ArrayList<File>();
//		for(File f : files) {
//			System.out.println(f.getAbsolutePath()+ " "
//					+ (f.getAbsolutePath().contains("8da21f41-f3eb-4ef3-ace0-360c3fcb41b3.jpg")? "o":"x"));
//		}
		for(File f : fileList) {
			for(Attach a : dbList) {
				if(f.getAbsolutePath().contains(a.getUuid())){
					existFile.add(f);
				}
			}
			//System.out.println(f.getAbsolutePath()+ " "
			//		+ (f.getAbsolutePath().contains("8da21f41-f3eb-4ef3-ace0-360c3fcb41b3.jpg")? "o":"x"));
		}
		System.out.println("========================");
		
		for(File f : existFile) {
			System.out.println(f);
		}
		
		fileList.removeAll(existFile);
		
		System.out.println("========================");
		
		for(File f : fileList) {
			System.out.println(f);
			f.delete();
		}
	}
}
