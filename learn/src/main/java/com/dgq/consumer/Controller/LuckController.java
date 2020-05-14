package com.dgq.consumer.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;

import com.dgq.consumer.service.LuckService;
import com.dgq.consumer.util.HttpClient;
import com.dgq.consumer.util.LuckUtil;

@RestController
public class LuckController{
	
//	@Autowired
//	private LuckDao LuckDao;
	
	@Autowired
	private LuckService luckservice;
	
	@RequestMapping(value = "dgq",method = RequestMethod.POST)
	public ResponseEntity<?> notify(@RequestParam String message,@RequestParam String sign){
		String test = System.getProperty("user.dir");
//		String path = this.getClass().getClassLoader().getResource("").getPath();
		String laststr = "";
		Path jsonPsth = Paths.get(new File(test).getParent(),"webapps/update.json");
		File file = jsonPsth.toFile();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			// int line=1;
			while ((tempString = reader.readLine()) != null) {
				// System.out.println("line"+line+":"+tempString);
				laststr = laststr + tempString;
				// line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException el) {
				}
			}
		}
		return new ResponseEntity<Object>(laststr, HttpStatus.OK);
	}
	
	@RequestMapping(value = "getMyLuck",method = RequestMethod.GET)
	public ResponseEntity<?> getLuck(String period){
		return new ResponseEntity<Object>(luckservice.getRandomLuck(), HttpStatus.OK);
	}

	@RequestMapping(value = "getAllLuck",method = RequestMethod.GET)
	public ResponseEntity<?> getAllLuck(String period){
		return new ResponseEntity<Object>(luckservice.getAllLuck(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "test",method = RequestMethod.GET)
	public ResponseEntity<?> getLuck() throws Exception{
		return new ResponseEntity<Object>(luckservice.insert(), HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "getNewLuck",method = RequestMethod.GET)
	public ResponseEntity<?> getNewLuck() throws Exception{
		return new ResponseEntity<Object>(luckservice.getNewLuck(), HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "getRandomLuck",method = RequestMethod.GET)
	public ResponseEntity<?> getRandomLuck() throws Exception{
		return new ResponseEntity<Object>(luckservice.getRandomLuck(), HttpStatus.OK);
	}
	
	public static void main(String[] args){
		String hmtl = HttpClient.httpPostUrlForPdf("https://m.aicai.com/kjgg/detailAll.do?agentId=2335098&vt=5&gameId=101&issueNo=2019152");
		Document document = Jsoup.parse(hmtl);
//		Iterator<Element> span = document.select("span[class=comm_ball red_ball]").iterator();
		Elements spans= document.select("div[class=lotteryBall]").select("em");
		String b = "";
		for(Element els:spans){
			if(els.toString().contains("blue")){
				b = b.substring(0, b.lastIndexOf(",")) + "-" + els.text();
			}else{
				b += els.text()+",";
			}
		}
		System.out.println(b);
//		int c = 0;
//		++c;
	}
}