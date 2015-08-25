package analysis;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SentenceTokenizer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

public class Sohu {
	
	public String getNewsContent(String url) throws Exception
	{
       core.GetHtml gh=new core.GetHtml();
		
		
		String html=gh.getHtml(url);
		int indexTile1=html.indexOf("<title>");
		int indexTile2=html.indexOf("</title>");
		if(indexTile1>=0&&indexTile2>=0&&indexTile1<indexTile2)
		{
			String titleStr=html.substring(indexTile1,indexTile2);
			if(titleStr.contains("ÊÓÆµ"))
				return "";
		}
		
		String retStr=html;
		String ret="";
		String str1="<div class=\"text clear\" id=\"contentText";
		int index0=retStr.indexOf(str1);
		retStr=retStr.substring(index0);
		
		String str2="(ÔðÈÎ±à¼­";
		
		int index1=retStr.indexOf(str2);
		
		ret=retStr.substring(0,index1);
		try{  
			
			
//			while(true)
//			{
//				String scipt0="<!DOCTYPE";
//				int indexSc0=ret.indexOf(scipt0);
//				if(indexSc0<0)
//					break;
//				String scipt1=">";
//				int indexSc1=ret.indexOf(scipt1, indexSc0+scipt0.length());
//				if(indexSc1<0)
//					break;
//				
//				String sub=ret.substring(indexSc0,indexSc1+scipt1.length());
//				ret=ret.replace(sub, "");
//				
//				
//			}
//			
			
			while(true)
			{
				try{
				String scipt0="<table";
				int indexSc0=ret.indexOf(scipt0);
				if(indexSc0<0)
					break;
				String scipt1="</table>";
				int indexSc1=ret.indexOf(scipt1, indexSc0+scipt0.length());
				if(indexSc1<0)
					break;
				
				
				
				String sub=ret.substring(indexSc0,indexSc1+scipt1.length());
				
				
				ret=ret.replace(sub, "");
				} catch(Exception ex){
					break;
				}
				
				
			}
			while(true)
			{
				try{String scipt0="<script>";
				int indexSc0=ret.indexOf(scipt0);
				if(indexSc0<0)
					break;
				String scipt1="</script>";
				int indexSc1=ret.indexOf(scipt1, indexSc0+scipt0.length());
				if(indexSc1<0)
					break;
				
				String sub=ret.substring(indexSc0,indexSc1+scipt1.length());
				ret=ret.replace(sub, "");
				}catch(Exception ex)
				{break;}
				
				
			}
				
               
			
			while(true)
			{
				String scipt0="<style";
				int indexSc0=ret.indexOf(scipt0);
				if(indexSc0<0)
					break;
				String scipt1="</style>";
				int indexSc1=ret.indexOf(scipt1, indexSc0+scipt0.length());
				if(indexSc1<0)
					break;
				
				String sub=ret.substring(indexSc0,indexSc1+scipt1.length());
				ret=ret.replace(sub, "");
				
				
			}
			
				
             

               
				
			while(true)
			{
				String scipt0="<div class=\"sele-con";
				int indexSc0=ret.indexOf(scipt0);
				if(indexSc0<0)
					break;
				String scipt1="</div>";
				int indexSc1=ret.indexOf(scipt1, indexSc0+scipt0.length());
				if(indexSc1<0)
					break;
				
				String sub=ret.substring(indexSc0,indexSc1+scipt1.length());
				ret=ret.replace(sub, "");
				
				
			}
				
           
			
			while(true)
			{
				String scipt0="<div class=\"r\"><strong>";
				int indexSc0=ret.indexOf(scipt0);
				if(indexSc0<0)
					break;
				String scipt1="</div>";
				int indexSc1=ret.indexOf(scipt1, indexSc0+scipt0.length());
				if(indexSc1<0)
					break;
				
				String sub=ret.substring(indexSc0,indexSc1+scipt1.length());
				ret=ret.replace(sub, "");
				
				
			}
				
               
			
			while(true)
			{
				String scipt0="<td class=\"text-pic-tt\"";
				int indexSc0=ret.indexOf(scipt0);
				if(indexSc0<0)
					break;
				String scipt1="</td>";
				int indexSc1=ret.indexOf(scipt1, indexSc0+scipt0.length());
				if(indexSc1<0)
					break;
				
				String sub=ret.substring(indexSc0,indexSc1+scipt1.length());
				ret=ret.replace(sub, "");
				
				
			}
				
                
			
			while(true)
			{
				String scipt0="¡¾";
				int indexSc0=ret.indexOf(scipt0);
				if(indexSc0<0)
					break;
				String scipt1="¡¿";
				int indexSc1=ret.indexOf(scipt1, indexSc0+scipt0.length());
				if(indexSc1<0)
					break;
				
				String sub=ret.substring(indexSc0,indexSc1+scipt1.length());
				ret=ret.replace(sub, "");
				
				
			}
				
             
			
			
		
			ret = ret.replaceAll("(\\&\\w+;)|(\\&#\\w+;)", "").replaceAll(
					"<[^>]*>", "");
			ret = ret.replaceAll("[(/>)<]", "");
			ret = ret.replaceAll("\\s+", "");

		}catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
//		if(ret.length()>140)
//			ret=ret.substring(0,140);
		ret=getTopStentence(ret);
		//System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
		return ret+"......";
	}
	
	public String getNews() throws Exception
	{
		core.GetHtml gh=new core.GetHtml();
		
		
		String html=gh.getHtml("http://news.sohu.com/s2007/newsreview/");
		
		String retStr=html;
		String ret="";
		String str1="<td class=f12_top>";
		int index0=retStr.indexOf(str1);
		retStr=retStr.substring(index0+str1.length());
		
		String str2="</td>";
		
		int index1=retStr.indexOf(str2);
		
		retStr=retStr.substring(0,index1);
		try{
		
		Pattern p=Pattern.compile("(<a href=)[^<]+(</a>)");
		
		 Matcher m=p.matcher(retStr);
		  int i=0;

		  while(m.find()&&i<10){
		   i++;
		   //System.out.println(m.group());
		   String title="";
		   String content="";
		   String strmGroup=m.group();
		   
		   Pattern p2=Pattern.compile("(_blank\">)[^<]+(</a>)");
		   Matcher m2=p2.matcher(strmGroup);
		   if(m2.find())
		   {
			   String m2group=m2.group();
			   String str10="_blank\">";
			  // System.out.println(m2.group());
			   m2group=m2group.substring(str10.length());
			   int index10=m2group.indexOf("</a>");
			   m2group=m2group.substring(0,index10);

			   title=m2group+"\n";
		   
		   }
		  
		   
		   Pattern p3=Pattern.compile("(href=\")\\S+(\"target)");

		   Matcher m3=p3.matcher(strmGroup);
		   if(m3.find())
		   {
			   String m3group=m3.group();
			   String str20="href=\"";
			   
			   
			   m3group=m3group.substring(str20.length());
			  
			   int index20=m3group.indexOf("\"target");
			   m3group=m3group.substring(0,index20);
			  

			  content=getNewsContent(m3group)+"\n\n";
			   
			  //ret+=m3group+"\n\n";
		   
		   }
		   if(!(title.equals("\n")||content.equals("\n\n")))
				   ret+=title+content;
		   
		   
		  }
		}catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
		return ret;
	}
	
	
	private String processLabelPais(String label_1,String label_2,String str)
	{
		ArrayList<String> list=new ArrayList<String>();
		
		String retStr=str;
		list.add(label_1);		
		int index0=str.indexOf(label_1);
		int index1=str.length();
		if(index0<0)
			return retStr;		
		int index=index0+label_1.length();
		
		while(list.size()>0)
		{
			int index_temp1=str.indexOf(label_1,index);
			int index_temp2=str.indexOf(label_2,index);
			
			System.out.println(index_temp1+"  "+index_temp2);
			if(index_temp1<0&&index_temp2<0)
			{
			   list.clear();
			   index1=index;
			   break;
			}
			
			if(index_temp1<0)
			{
				list.remove(list.size()-1);
				if(list.size()<=0)
				{
					index1=index_temp2;
					break;
				}
				index=index=index_temp2+label_2.length();
			}
			
			if(index_temp2<index_temp1&&index_temp2>0)
			{
				list.remove(list.size()-1);
				if(list.size()<=0)
				{
					index1=index_temp2;
					break;
				}
				index=index=index_temp2+label_2.length();
			}
			
			if(index_temp1<index_temp2&&index_temp1>0)
			{
				list.add(label_1);
				index=index_temp1+label_1.length();
			}
			
				
		}
		
		retStr=str.substring(index0,index1);
		return retStr;
	
	}
	private  String getTopStentence(String text) throws IOException, ParseException 
	{
		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_45);
		StringReader sr = new StringReader(text);
		SentenceTokenizer ts = new SentenceTokenizer(sr);
		boolean hasnext = ts.incrementToken();
		String str="";
		Map<String,Double> map = new HashMap<String,Double>();
		Map<String,Integer> map2 = new HashMap<String,Integer>();
		int index=0;
		while(hasnext){
			CharTermAttribute ta = ts.getAttribute(CharTermAttribute.class);
	        String sub_sentence=ta.toString();
	       
	        str+=sub_sentence;
	        if(sub_sentence.endsWith("¡£")||sub_sentence.endsWith("!")||sub_sentence.endsWith("¡±"))
	        {
	        	 if(str.indexOf("http")<0&&str.indexOf("div")<0){
	        		 double weight=getTFIDF(str);
			          //System.out.println(weight+"    "+str);	
			          map.put(str, weight);
			          map2.put(str, index);
			          
			          
			          index++;
	        	 }
	        	 str="";
	        	
	        }		
		               
			hasnext = ts.incrementToken();
			
		}
//		if(!str.equals("")&&!str.contains("http"))
//		{
//			double weight=getTFIDF(str);
//	         // System.out.println(weight+"    "+str);
//			 map.put(str, weight);
//		}
//		TreeMap<String,Double> treemap = new TreeMap<String,Double>(Collections.reverseOrder());
//		treemap.putAll(map);
//		Iterator it = treemap.keySet().iterator();
//		while (it.hasNext()) {
//			String key=(String) it.next();
//			double weight=treemap.get(key);
//			System.out.println(weight+" "+key);
//		}
		
		//Map<String, Integer> keyfreqs = new HashMap<String, Integer>();

		ArrayList<Entry<String,Double>> l = new ArrayList<Entry<String,Double>>(map.entrySet());  
		Collections.sort(l, new Comparator<Map.Entry<String, Double>>() {  
			public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {  
				if (o2.getValue()> o1.getValue())
					return 1;
				else if(o2==o1) return 0;
				else return -1;
			}  
		});
		int topNum=3;
		
		Map<String,Integer> map3 = new HashMap<String,Integer>();

		for(Entry<String,Double> e : l) {
			//System.out.println( e.getValue()+" "+e.getKey() );
			map3.put(e.getKey(), map2.get(e.getKey()));
			topNum--;
			if(topNum<=0)
				break;
		}
		ArrayList<Entry<String,Integer>> l2 = new ArrayList<Entry<String,Integer>>(map3.entrySet()); 
		
		Collections.sort(l2, new Comparator<Map.Entry<String, Integer>>() {  
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {  
				return o2.getValue()-o1.getValue();
					
			}  
		});
		String retStr="";
		for(Entry<String,Integer> e : l2) {
			//System.out.println(map.get(e.getKey())+" "+e.getKey() );
			//retStr+=map.get(e.getKey())+" "+e.getKey()+" "+e.getKey().indexOf("http")+"\n";
			retStr+=e.getKey()+"...";
			
		}
		
		return retStr;
				
				
				
				
		
	  
	}
	
	private  double getTFIDF(String sentence) throws IOException, ParseException 
	{

		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_45);
		File file=new File("E:\\test11");
	    Directory index = new SimpleFSDirectory(file);
		IndexReader reader = DirectoryReader.open(index);
		
		StringReader sr = new StringReader(sentence);
		TokenStream ts=analyzer.tokenStream("content", sr);
	      Map<String, Integer> frequencies = new HashMap<String, Integer>();
	    List<String> termVec=new ArrayList<String>();
		//TokenStream tk=analyzer.tokenStream("filed", sr);
		//SentenceTokenizer tk = new SentenceTokenizer(sr);
		boolean hasnext = ts.incrementToken();
		while(hasnext){
			CharTermAttribute ta = ts.getAttribute(CharTermAttribute.class);
	        String term=ta.toString();
			//System.out.println(term+" "+reader.getSumDocFreq(ta.toString()));
			if(!termVec.contains(term))
			{
				termVec.add(term);
				frequencies.put(term, 1);
			}
			else
			{
				int freq=frequencies.get(term);
				freq++;
				frequencies.put(term, freq);
			}
			hasnext = ts.incrementToken();
		
		}
		
		int numTerm=0;
        double sumWeight=0;

	    for(int i=0;i<termVec.size();i++)
	    {
	    	String term=termVec.get(i);
	    	int freq=frequencies.get(term);
	    	if(term.length()>1)
	    	{

                Term ter=new Term("content",term);
                numTerm++;
	    		
	    		double idf=Math.log((double)reader.numDocs()/(double)(reader.docFreq(ter)+1));
	    		double weight=(double)freq*idf;
	    		sumWeight=sumWeight+weight;
	    		//System.out.println(term+"       "+freq+" "+reader.docFreq(ter)+" "+weight);
	    	}
	    }
	    reader.close();
	    return (double)sumWeight/(double)(numTerm+1);
	    
	}
	
	
	
	
	
	


}
