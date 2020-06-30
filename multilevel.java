import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class multilevel {
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


	public boolean L2find(Cache2 cache,int s,int S) {
		boolean b=false;
		if(y.contains(s)) {
			cache.hits++;
			y1.add(s);
			b=true;
		}
		else {cache.misses++;b=false;replacement(cache,s,S);
		}
		return b;}

	public boolean L1find(Cache2 cache, int[] arr, String[] array,String[] array2,Cache2 cache2,int CL) {

		 Boolean b=false;
		 for(int i=0;i<array.length;i++){
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
			else if(array2[i].compareTo("W")==0 && y1.contains(arr[i])==false) {
				cache.writes++;
				y1.add(arr[i]);
			}
			if(y1.contains(arr[i])) {
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
				boolean m=L2find(cache2,arr[i],CL);
				if(m==true) {
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
		multilevel obj=new multilevel();
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

		//int counter=0;
		for(int i=0;i<arr.length;i++) {
			String[] x=arr[i].split(" ");	
			int dec=Integer.valueOf(x[2]);
			memerq[i]=dec;
			mem1[i]=x[0];
			memrq1[i]=x[1];
		}
		obj.L1find(L1cache,memerq,mem1,memrq1, L2cache,CL);  
		for(int m=0;m<y1.size();m++) {
			System.out.println(" "+ y1.get(m));
		}
		obj.destroyCache(L1cache);
		obj.destroyCache(L2cache);

	}
	}
}


