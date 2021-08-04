package com.dima.test.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.dima.test.dto.UserImage;
import com.dima.test.service.ImageService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/user")
public class ImageController {
	@Autowired
	private ImageService imageservice;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@ApiOperation(value = "프로필사진 변경", response = String.class)
	@PutMapping("/modify/userImage")
	public ResponseEntity<String> modifyUserImage(@RequestPart  MultipartFile  userimage, @RequestParam String userId) throws Exception {
		logger.debug("프로필사진 변경");
		String imagepath = imageservice.selectImagePath(userId);
		String imageName = imageservice.selectImageName(userId);
		File deletefile = new File(imagepath+imageName);
		System.out.println(deletefile);
		deletefile.delete();
		
		UserImage file = new UserImage();
		String sourceFileName= userimage.getOriginalFilename();
		String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase(); 
		File destinationFile;
		String destinationFileName;
//		String fileUrl = "C:\\jobara\\Image\\";
//		String fileUrl ="C:\\PJT\\front4\\front\\src\\assets\\images\\profile\\";
		String fileUrl ="C:\\PJT\\PJTfront\\front\\public\\img\\";
			do { 
        			destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension; 
        			destinationFile = new File(fileUrl + destinationFileName); 
    		} while (destinationFile.exists()); 
    
    		destinationFile.getParentFile().mkdirs(); 
    		userimage.transferTo(destinationFile);
    		file.setUserId(userId);
    	
    		file.setUserimagePath(fileUrl);
    		imageservice.updateUserImage(file);
    		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	/*local image불러오기*/
	@ApiOperation(value = "프로필사진 불러오기", response = String.class)
	   @PostMapping("/userImage5")
//	   public ResponseEntity<Resource> selectUserImage(@RequestBody String userId) throws Exception {
	   public ResponseEntity<String> selectUserImage5(@RequestBody String userId) throws Exception {
	      System.out.println(userId);
//	      String imagepath = imageservice.selectImagePath(userId);
	      String imagepath = "/img/";
//	      System.out.println("imagepath: "+imagepath);
	      String imageName = imageservice.selectImageName(userId);
	      System.out.println("imageName: "+ imageName);
//	      Path path=Paths.get(imagepath + imageName); // \가 됨 ㅠㅠ
	      String pathString=imagepath+imageName;
//	      String pathString=imageName;
	      System.out.println("path: "+ pathString);
//	      String contentType=Files.probeContentType(path);
	      Path path=Paths.get(pathString); // \가 됨 ㅠㅠ
	      return new ResponseEntity<String>(pathString, HttpStatus.OK);
	   }
//////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////AWS - USER/////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
	
	@ApiOperation(value = "(AWS)사용자가 올린 프로필사진 경로 저장하기", response = String.class)
	@PostMapping("/insertAWSUserImage")
	 public ResponseEntity<String> insertAWSUserImage(@RequestBody UserImage userImage) throws Exception {
		logger.debug("(AWS)사용자가 올린 프로필사진 경로 저장하기");
		if(imageservice.insertAWSUserImage(userImage)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	   }
	
	@ApiOperation(value = "(AWS)사용자가 올린 프로필사진 경로 불러오기", response = String.class)
	@PostMapping("/selectAWSUserImage")
	 public ResponseEntity<String> selectAWSUserImage(@RequestBody String userId) throws Exception {
	      return new ResponseEntity<String>(imageservice.selectAWSUserImage(userId), HttpStatus.OK);
	   }
	
	@ApiOperation(value = "(AWS)사용자가 올린 프로필사진 경로 수정하기", response = String.class)
	@PostMapping("/updateAWSUserImage")
	 public ResponseEntity<String> updateAWSUserImage(@RequestBody UserImage userImage) throws Exception {
		if(imageservice.updateAWSUserImage(userImage)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	   }
	
	@ApiOperation(value = "(AWS)사용자가 올린 프로필사진 경로 삭제하기", response = String.class)
	@PostMapping("/deleteAWSUserImage/{userId}")
	 public ResponseEntity<String> deleteAWSUserImage(@PathVariable String userId) throws Exception {
		if(imageservice.deleteAWSUserImage(userId)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}

//	@ApiOperation(value = "프로필사진 불러오기1", response = String.class)
//	@GetMapping("/userImage/{userId}")
//	public ResponseEntity<Resource> selectUserImage(@RequestParam String userId) throws Exception {
////	   public ResponseEntity<Resource> selectUserImage(@RequestBody String userId) throws Exception {
////	   public ResponseEntity<String> selectUserImage(@RequestBody String userId) throws Exception {
//	      System.out.println(userId);
////			String userId="string";
////	      String imagepath = imageservice.selectImagePath(userId);
////	      String imagepath = "../../assets/images/profile/";
////	      System.out.println("imagepath: "+imagepath);
//	      String imageName = imageservice.selectImageName(userId);
//	      System.out.println("imageName: "+ imageName);
//	      
//	      Path path=Paths.get(imageName); // \가 됨 ㅠㅠ
////	      String pathString=imagepath+imageName;
//	      System.out.println("path: "+ path);
//	      String contentType=Files.probeContentType(path);
//	      System.out.println("contentType: "+contentType);
//	      
////	      return new ResponseEntity<String>(pathString, HttpStatus.OK);
//	      HttpHeaders headers = new HttpHeaders();
//	      headers.add(HttpHeaders.CONTENT_TYPE, contentType);
//	     
//	      
//	      Resource resource = new InputStreamResource(Files.newInputStream(path));
//	      System.out.println("resource: "+resource+"|||headersType: "+headers);
//	      return new ResponseEntity<Resource>(resource,headers, HttpStatus.OK);
//  }


//	
//	@GetMapping("/userImage2")
//	public ResponseEntity<Resource>selectUserImage2(@RequestParam String userId)throws Exception{
//		String imagepath = imageservice.selectImagePath(userId);
//		System.out.println(imagepath);
//		String imageName = imageservice.selectImageName(userId);
//		System.out.println(imageName);
//		File file = new File(imagepath+imageName);
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//		headers.add("Pragma", "no-cache");
//		headers.add("Expires", "0");
//		Path path = Paths.get(file.getAbsolutePath());
//		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
//		return ResponseEntity.ok().headers(headers).contentLength(file.length())
//				.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
//	}
//	@GetMapping(value="/userImage3", produces=MediaType.IMAGE_JPEG_VALUE)
//	public ResponseEntity<Resource>selectUserImage3(@RequestParam String userId)throws Exception{
//		String imagepath = imageservice.selectImagePath(userId);
//		System.out.println(imagepath);
//		String imageName = imageservice.selectImageName(userId);
//		System.out.println(imageName);
//		File file = new File(imagepath+imageName);
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//		headers.add("Pragma", "no-cache");
//		headers.add("Expires", "0");
//		Path path = Paths.get(file.getAbsolutePath());
//		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
//		return ResponseEntity.ok().headers(headers).contentLength(file.length())
//				.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
//	}
//	
//	@GetMapping(value="/userImage4", produces=MediaType.IMAGE_JPEG_VALUE)
//	public ResponseEntity<byte[]>selectUserImage4(@RequestParam String userId)throws Exception{
//		String imagepath = imageservice.selectImagePath(userId);
//		System.out.println(imagepath);
//		String imageName = imageservice.selectImageName(userId);
//		System.out.println(imageName);
//		InputStream in = getClass().getResourceAsStream(imagepath+imageName);
//		System.out.println(in);
//		return  new ResponseEntity<byte[]>(IOUtils.toByteArray(in), HttpStatus.OK);
//	}
//	
}
