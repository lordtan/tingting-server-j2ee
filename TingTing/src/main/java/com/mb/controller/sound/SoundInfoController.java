package com.mb.controller.sound;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mb.beans.common.Message;
import com.mb.service.ISoundService;
import com.mb.service.sound.SoundService;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

/**
 * 收听声音，发布声音服务
 * @author lordtan
 * @date 2015年6月1日
 */
@Controller
@RequestMapping("/sound")
public class SoundInfoController {

	@Resource(type=SoundService.class)
	private ISoundService soundService;
	
	/**
	 * 查询目标附近的声音
	 * @param search  @see com.mb.beans.momgo.Search
	 * @return
	 */
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public @ResponseBody Message search(@RequestBody String search){
		return soundService.search(search);
	}
	
	/**
	 * 收听声音
	 * @param fileId 文件id
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/listen",method=RequestMethod.POST)
	public void listen(@RequestBody String fileId, HttpServletRequest request, HttpServletResponse response){
		GridFSDBFile file = soundService.listen(fileId);
		
        try {
        	response.setContentType("application/x-msdownload;");   
			response.setHeader("Content-disposition", "attachment; filename="  
			        + new String(fileId.getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(file.getLength())); 
		   
			file.writeTo(response.getOutputStream()); //把文件写向输出流
			 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}   
	}
	
	/**
	 * 存储声音实体文件
	 * @param fileId 文件id
	 * @param request 请求
	 * @param response 响应
	 */
	@RequestMapping(value="/store",method=RequestMethod.POST)
	public void store(@RequestBody String fileId, @RequestBody String type, HttpServletRequest request, HttpServletResponse response){
		try {
			soundService.store(request.getInputStream(), fileId, type);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 发布声音
	 * @param sound 声音相关信息
	 * @return
	 */
	@RequestMapping(value="/publish",method=RequestMethod.POST)
	public @ResponseBody Message publish(@RequestBody String sound){
		return soundService.publish(sound);
	}
	
	/**
	 * 取消发布
	 * @param sound
	 * @return
	 */
	@RequestMapping(value="/cancel",method=RequestMethod.POST)
	public @ResponseBody Message cancel(@RequestBody String sound){
		return soundService.cancel(sound);
	}
	
	
}
