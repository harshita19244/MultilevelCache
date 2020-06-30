import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class multilevel2 {
	static int memory=128;  
	static ArrayList<Integer> y=new ArrayList<Integer>();
	static ArrayList<Integer> y1=new ArrayList<Integer>();
	class Block {

		char[] tag;
		char[] index;
		char[] offset;
	}

	static class Cache2 {
		int hits;
		int misses;
		int reads;
		int writes;
		int cache_size;
		int block_size;
		int numLines;
		int write_policy;
		Block[] blocks;    
	}

	static class Reader{
		static BufferedReader reader;
		static StringTokenizer tokenizer;

		/** call this method to initialize reader for InputStream */
		static void init(InputStream input) {
			reader = new BufferedReader(
					new InputStreamReader(input) );
			tokenizer = new StringTokenizer("");
		}

		/** get next word */
		static String next() throws IOException {
			while ( ! tokenizer.hasMoreTokens() ) {
				//TODO add check for eof if necessary
				tokenizer = new StringTokenizer(
						reader.readLine() );
			}
			return tokenizer.nextToken();
		}

		static int nextInt() throws IOException {
			return Integer.parseInt( next() );
		}

		static double nextDouble() throws IOException {
			return Double.parseDouble( next() );
		}
	}
	public void destroyCache(Cache2 cache)
	{if(cache != null)cache=null;return;}

	public Cache2 createCache(int cache_size, int block_size,int lines)
	{if(cache_size <= 0)
	{
		System.out.println("Cache size must be greater than 0 bytes...\n");
		return null;
	}

	if(block_size <= 0)
	{
		System.out.println("Block size must be greater than 0 bytes...\n");
		return null;
	}
	else {
		Cache2 cache = new Cache2();
		cache.hits = 0;
		cache.misses = 0;
		cache.reads = 0;
		cache.writes = 0;
		//cache.write_policy = write_policy;
		cache.cache_size = cache_size;
		cache.block_size = block_size;
		cache.numLines = lines;
		return cache;
	}}

	public void replacement(Cache2 cache,int s,int CL) {
		int g=0;
		if(y1.size()<CL) {
			//y.add(s);
			y1.add(s);
			System.out.println("L1 and L2 obtained a miss");
			System.out.println("Data was loaded in cache");
			
		}
		else if(y1.size()>=CL) {
			g=y1.remove(0);
			y1.add(s);
			y.add(g);
			System.out.println("L1 and L2 obtained a miss");
			System.out.println("Removed:" + g);
			System.out.println("Replaced:" + s);
		}

	}


	public boolean L2find(Cache2 cache,int s,int S,int tag1,int index1,int offset1) {
		boolean b=false;
		int number=1;
		String f="";
		String h="";
		char[] bstring;
		   if(memory==64) {
		    	f="000000";
		    }
		    else if(memory==128) {
		    	f="0000000";
		    }
		    else if(memory==256) {
		    	f="00000000";
		    }
		   
		   //strings.add(f.substring(Integer.toBinaryString(s).length())+ Integer.toBinaryString(s);
		   h=f.substring(Integer.toBinaryString(s).length())+ Integer.toBinaryString(s);
		   bstring =h.toCharArray();
		   char[] tag =new char[tag1];
		   for(int m=0;m<tag1;m++) {
		     tag[m]=bstring[m];}
		
		    char[] index=new char[index1];
		    
		    for(int m = tag1; m < index1 + tag1; m++)
		    {
		        index[m - tag1] = bstring[m];
		        
		    }
		    
		    char[] offset = new char [offset1];
		    
		    for(int m = index1 + tag1; m < offset1 + index1 + tag1; m++)
		    {    
		    	
		        offset[m - index1 - tag1] = bstring[m];
		       
		    }
		    
		    StringBuilder sb = new StringBuilder();
	    	for(int i3=0;i3<tag1;i3++) {
	    	sb.append(tag[i3]);
	    	}
	    	
	    	String str = sb.toString();
	    	
	    	StringBuilder sb1 = new StringBuilder();
	    	for(int i3=0;i3<index1;i3++) {
	    	sb1.append(index[i3]);
	    	}
	    	
	    	String stringn = sb1.toString();
	    	if(y.size()>1) {
	    	 for(int r=0;r<y.size();r++) {
	             String p=f.substring(Integer.toBinaryString(y.get(r)).length())+ Integer.toBinaryString(y.get(r));
	        	 if(p.substring(0,tag1).equals(str) && p.substring(tag1,index1+tag1).equals(stringn)) {
	        		number=0;
	        		break;
	        	}
	    	 }
	       }
		if(y.contains(s)||number==0) {
			cache.hits++;
			y1.add(s);
			b=true;
		}
		else {cache.misses++;b=false;replacement(cache,s,S);
		}
		return b;}

	public boolean L1find(Cache2 cache, int[] arr, String[] array,String[] array2,Cache2 cache2,int CL,int index1,int offset1,int tag1) {

		 Boolean b=false;
		 String f="";
		 char[] bstring;
		 String h="";
		 ArrayList<String> strings=new ArrayList<String>();
		 //System.out.println(arr.length);
		 for(int i=0;i<arr.length;i++){
			 int number=1;
			 if(array[i].compareTo("wt")==0)
				{
					System.out.println(" ");
					System.out.println("Write Policy: Write Through\n");
				}
				else if(array[i].compareTo("wb")==0)
				{
					cache.writes++;
					System.out.println(" ");
					System.out.println("Write Policy: Write Back\n");
				}

				if(array2[i].compareTo("R")==0) {
					cache.reads++;
				}
				else if(array2[i].compareTo("W")==0) {
					cache.writes++;
					y1.add(arr[i]);
				}
				
			  
			   if(memory==64) {
			    	f="000000";
			    }
			    else if(memory==128) {
			    	f="0000000";
			    }
			    else if(memory==256) {
			    	f="00000000";
			    }
			   
			   //strings.add(f.substring(Integer.toBinaryString(arr[i]).length())+ Integer.toBinaryString(arr[i]));
			   h=f.substring(Integer.toBinaryString(arr[i]).length())+ Integer.toBinaryString(arr[i]);
			   bstring =h.toCharArray();
			   char[] tag =new char[tag1];
			   for(int m=0;m<tag1;m++) {
			     tag[m]=bstring[m];}
			
			    char[] index=new char[index1];
			    
			    for(int m = tag1; m < index1 + tag1; m++)
			    {
			        index[m - tag1] = bstring[m];
			        
			    }
			    
			    char[] offset = new char [offset1];
			    
			    for(int m = index1 + tag1; m < offset1 + index1 + tag1; m++)
			    {    
			    	
			        offset[m - index1 - tag1] = bstring[m];
			       
			    }
			    
			    StringBuilder sb = new StringBuilder();
		    	for(int i3=0;i3<tag1;i3++) {
		    	sb.append(tag[i3]);
		    	}
		    	
		    	String str = sb.toString();
		    	
		    	StringBuilder sb1 = new StringBuilder();
		    	for(int i3=0;i3<index1;i3++) {
		    	sb1.append(index[i3]);
		    	}
		    	
		    	String stringn = sb1.toString();
		    	if(y1.size()>1) {
		    	 for(int r=0;r<y1.size();r++) {
		             String p=f.substring(Integer.toBinaryString(y1.get(r)).length())+ Integer.toBinaryString(y1.get(r));
		        	 if(p.substring(0,tag1).equals(str) && p.substring(tag1,index1+tag1).equals(stringn)) {
		        		number=0;
		        		break;
		        	}
		    	 }
		       }
			    if(y1.contains(arr[i])||number==0) {
						cache.hits++;
						b=true;
						System.out.println("L1 obtained a hit:Schematic of L1");
						System.out.println("CACHE HITS:" +" "+ cache.hits);
						System.out.println("CACHE MISSES:" +" "+ cache.misses);
						System.out.println("MEMORY READS:" +" "+cache.reads);
						System.out.println("MEMORY WRITES:" +" "+ cache.writes);
						System.out.println("CACHE SIZE:" +" "+ cache.cache_size);
						System.out.println("BLOCK SIZE:" +" "+ cache.block_size);
						System.out.println("NUMBER OF LINES:" +" "+ cache.numLines);}

					else {
						cache.misses++;b=false;
						boolean bb=L2find(cache2,arr[i],CL,tag1,index1,offset1);
						if(bb==true) {
							System.out.println("L2 obtained a hit:Schematic of L2");
							System.out.println("CACHE HITS:" +" "+ cache2.hits);
							System.out.println("CACHE MISSES:" +" "+ cache2.misses);
							System.out.println("MEMORY READS:" +" "+cache2.reads);
							System.out.println("MEMORY WRITES:" +" "+ cache2.writes);
							System.out.println("CACHE SIZE:" +" "+ cache2.cache_size);
							System.out.println("BLOCK SIZE:" +" "+ cache2.block_size);
							System.out.println("NUMBER OF LINES:" +" "+ cache2.numLines);}
					}
					
				}
		return b;
				
}

	public static void main(String args[]) throws IOException {
		Reader.init(System.in);
		int S=Reader.nextInt();
		int CL=Reader.nextInt();
		int B=Reader.nextInt();
		int flag=Reader.nextInt();
		multilevel2 obj=new multilevel2();
		Cache2 L1cache = obj.createCache(S/2,B,CL);
		Cache2 L2cache = obj.createCache(S,B,CL);
		if(flag==1) {
		FileReader fr=new FileReader("testout1.txt");
		FileReader fr2=new FileReader("testout.txt");
		BufferedReader br=new BufferedReader(fr);
		StringBuffer sb=new StringBuffer();
		String line;

		while((line=br.readLine())!=null)  
		{  
			sb.append(line);      //appends line to string buffer  
			sb.append("\n");     //line feed   
		}  
		fr.close();    //closes the stream and release the resources  
		
		String[] lines = sb.toString().split("\\n");
		
		for(int i=0;i<lines.length;i++) {

			String[] x=lines[i].split(" ");	
			int dec=Integer.valueOf(x[2]);
			if(x[0].compareTo("wt")==0)
			{
				//System.out.println("Write Policy: Write Through\n");
			}
			else if(x[0].compareTo("wb")==0)
			{
				L1cache.writes++;
				//System.out.println("Write Policy: Write Back\n");
			}

			if(x[1].compareTo("R")==0) {
				L1cache.reads++;
			}
			else if(x[1].compareTo("W")==0) {
				L1cache.writes++;
			}
			y1.add(dec);}
		System.out.println("Contents of L1");
		for (int i=0; i<y1.size(); i++) {
			System.out.print(y1.get(i)+" "); 
		} 
		System.out.println(" ");
		BufferedReader br1=new BufferedReader(fr2);
		StringBuffer sb1=new StringBuffer();
		String line1;

		while((line1=br1.readLine())!=null)  
		{  
			sb1.append(line1);      //appends line to string buffer  
			sb1.append("\n");     //line feed   
		}  
		fr2.close();    //closes the stream and release the resources  
		
		String[] lines2 = sb1.toString().split("\\n");

		for(int i=0;i<lines2.length;i++) {

			String[] x=lines2[i].split(" ");	
			int dec=Integer.valueOf(x[2]);
			if(x[0].compareTo("wt")==0)
			{
				//System.out.println("Write Policy: Write Through\n");
			}
			else if(x[0].compareTo("wb")==0)
			{
				L2cache.writes++;
				//System.out.println("Write Policy: Write Back\n");
			}

			if(x[1].compareTo("R")==0) {
				L2cache.reads++;
			}
			else if(x[1].compareTo("W")==0) {
				L2cache.writes++;
			}
			y.add(dec);}

		System.out.println("Contents of L2");
		for (int i=0; i<y.size(); i++) {
			System.out.print(y.get(i)+" "); 
		} 
		}
		if(flag==0) {
		System.out.println(" ");
		FileReader mem=new FileReader("requests.txt");
		BufferedReader brx=new BufferedReader(mem);
		StringBuffer sbx=new StringBuffer();
		String linesx;
		while((linesx=brx.readLine())!=null)  
		{  
			sbx.append(linesx);      //appends line to string buffer  
			sbx.append("\n");     //line feed   
		}  
		mem.close();    //closes the stream and release the resources
		System.out.println(" ");
		System.out.println("Contents of File: ");  
		System.out.println(sbx.toString());   //returns a string that textually represents the object  
		String[] arr = sbx.toString().split("\\n");
		int[] memerq=new int[arr.length];
		String[] memrq1=new String[arr.length];
		String[] mem1=new String[arr.length];
        for(int i=0;i<arr.length;i++) {
			String[] x=arr[i].split(" ");	
			int dec=Integer.valueOf(x[2]);
			memerq[i]=dec;
			mem1[i]=x[0];
			memrq1[i]=x[1];
		}
		int index=(int)(Math.log(CL) / Math.log(2)); 
        int offset=(int)(Math.log(B) / Math.log(2)); 
        int tag=((int)(Math.log(memory) / Math.log(2))-index-offset);
		obj.L1find(L1cache,memerq,mem1,memrq1, L2cache,CL,index,offset,tag); 
		for(int m=0;m<y1.size();m++) {
			System.out.println(" "+ y1.get(m));
		}
		obj.destroyCache(L1cache);
		obj.destroyCache(L2cache);

	}
	}
}

