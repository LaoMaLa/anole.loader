package org.tbwork.anole.loader.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

public class FileUtil {

	
	public static class AnoleFilePath {
		private List<String> pathPartList;
		
		public AnoleFilePath(String fullPath){
			pathPartList = Lists.newArrayList(fullPath.split("\\\\|/"));
		}
		
		public boolean  isFuzzyDirectory(){
			int i = 0;
			for(i = 0; i < pathPartList.size(); i++){
				if(pathPartList.get(i).contains("*")){
					break;
				}
			}
			return i < pathPartList.size()-1;
		}
		
		public String getSolidDirectory(){
			int i = 0;
			for(i = 0; i < pathPartList.size(); i++){
				if(pathPartList.get(i).contains("*")){
					break;
				}
			}
			return StringUtil.join("/", pathPartList.subList(0, Math.min(i, pathPartList.size()-1))) + "/";
		}
		
		public boolean match(AnoleFilePath afp){
			return this.pathPartList.size() == afp.pathPartList.size();
		}
	}
	
	public static String getSolidDirectory(String fullPath){
		AnoleFilePath afp = new AnoleFilePath(fullPath);
		return afp.getSolidDirectory();
	}
	
	public static boolean isFuzzyDirectory(String fullPath){
		AnoleFilePath afp = new AnoleFilePath(fullPath);
		return afp.isFuzzyDirectory();
	} 
	
	
	public static List<File> getFilesInDirectory(String path){
		List<File> result = new ArrayList<File>();
		File file = new File(path);
		File[] fileList = file.listFiles();
		for (int i = 0; i < fileList.length; i++) {
			File tempFile = fileList[i];
			if(tempFile.isDirectory()){
				result.addAll(getFilesInDirectory(tempFile.getAbsolutePath()));
			}
			else{
				result.add(tempFile);
			} 
		} 
		return result;
	}
	
	public static String toLinuxStylePath(String path){
		if(!path.startsWith("/"))
			path = "/"+path; 
		return path.replace("\\\\", "/").replace("\\", "/");
	}
	 
	/**
	 * Match the asterisk file path and the target actual file path.
	 * <p>The matched result should fulfill two conditions:<br>
	 * <b>1.</b>The directory depth of the asterisk file is same to the asterisk file path. <br>
	 * <b>2.</b>The target file path matches the asterisk file in text. <br>
	 * @param asteriskPath the asterisk file's full path
	 * @param targetPath the target actual file's full path
	 * @return true means matched successfully, otherwise means failed.
	 */
	public static boolean asteriskMatchPath(String asteriskPath, String targetPath){
		AnoleFilePath afp1 = new AnoleFilePath(asteriskPath);
		AnoleFilePath afp2 = new AnoleFilePath(targetPath);
		return afp1.match(afp2) && StringUtil.asteriskMatch(asteriskPath, targetPath); 
	}
}
