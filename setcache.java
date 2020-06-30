import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class setcache {
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

	public void LRUreplacement(Cache2 cache,int S,int CL,int B,int[] arr1,String[] arr2,String[] arr3,int count,int k) {
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
		return b;

	}


	public boolean L1find(int B,int k,Cache2 cache, int[] arr, String[] array,String[] array2,Cache2 cache2,int CL) {

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
			
			ArrayList<Integer> r=new ArrayList<Integer>();
			ArrayList<Integer> r1=new ArrayList<Integer>();
			ArrayList<Integer> r2=new ArrayList<Integer>();
			ArrayList<Integer> r3=new ArrayList<Integer>();
			//int y=S/CL;
			int[][] yt=new int[CL][1000];
			int g=0;
			int g1=0;
			int g2=0;
			int g3=0;
			int count0 = 0,count1=0,count2=0,count3=0,count4 = 0,count5=0,count6=0,count7=0;
			int count8 = 0,count9=0,count10=0,count11=0,count14 = 0,count15=0,count12=0,count13=0;
            int count=0;
				if(CL==4 && k==2) {
					int set_no=arr[i]%k;
					System.out.println("set_no:"+set_no);
					
					if (count!=0 && (r.contains(arr[i])||r1.contains(arr[i]))){
						cache.hits++;
						System.out.println(" ");
						System.out.println(count+" "+"Cache obtained a hit ");
						if(k==2) {
							if(r.contains(arr[i])) {
								count1++;
								count0++; 
							}
							else if(r1.contains(arr[i])) {
								count3++;
								count2++;}}count++;}

					else{
						cache.misses++;
						System.out.println(" ");
						System.out.println(count+" " + "Cache obtained a miss ");
						//System.out.println(count1);
						if(set_no==0 && g==0) {
							yt[0][count]=arr[i];
							r.add(arr[i]);
							count0++;
							g++;
						}
						else if(set_no==0 && g==1) {
							yt[1][count]=arr[i];
							r.add(arr[i]);
							count0++;
							count1++;
							g++;
						}
						else if(set_no==1 && g1==0) {
							yt[2][count]=arr[i];
							r1.add(arr[i]);

							count2++;
							g1++;
						}
						else if(set_no==1 && g1==1) {
							yt[3][count]=arr[i];
							r1.add(arr[i]);

							count2++;
							count3++;
							g1++;
						}

						else {
							System.out.println(" ");
							System.out.println(count+" "+"A replacement was carried out ");
							System.out.println("Replaced:"+arr[i]);
							int m=arr[i]%k;
							int a=0;
							if(m==0) {
								a= Math.max(count0, count1);if(a==count0) {
									yt[0][count]=arr[i];
									count0=0;int y1=r.remove(0);System.out.println("Removed: "+y1);
									count1++;r.add(arr[i]);}else if(a==count1) {
										yt[1][count]=arr[i];
										count1=0;int y1=r.remove(0);
										System.out.println("Removed: "+y1);
										count0++;r.add(arr[i]);

									}
							}
							else if(m==1) {
								a=Math.max(count2, count3);if(a==count2) {yt[2][count]=arr[i];
								count2=0;int y1=r1.remove(0);
								System.out.println("Removed: "+y1);count3++;r1.add(arr[i]);
								}else if(a==count3) {
									yt[3][count]=arr[i];
									count3=0;int y1=r1.remove(0);System.out.println("Removed: "+y1);count2++;r1.add(arr[i]);

								}
							}

						}count++;}
					System.out.println(" ");
					System.out.println("Num Lines:"+" " +count);
					System.out.println("CACHE HITS:" +" "+ cache.hits);
					System.out.println("CACHE MISSES:" +" "+ cache.misses);
					System.out.println("MEMORY READS:" +" "+ cache.reads);
					System.out.println("MEMORY WRITES:" +" "+ cache.writes);
					System.out.println("CACHE SIZE:" +" "+ cache.cache_size);
					System.out.println("BLOCK SIZE:" +" "+ cache.block_size);
					System.out.println("NUMBER OF LINES:" +" "+ cache.numLines);
				}

				else if(k==2 && CL==8) {

					if (count!=0 && (r.contains(arr[i])||r1.contains(arr[i])||r2.contains(arr[i])||r3.contains(arr[i]))){
						cache.hits++;
						System.out.println(" ");
						System.out.println(count+" "+"Cache obtained a hit ");
						if(r.contains(arr[i])) {count0++;count1++;}
						else if(r1.contains(arr[i])) {count3++;count2++;}
						else if(r2.contains(arr[i])) {count4++;count5++;}
						else if(r3.contains(arr[i])) {count6++;count7++;}
						count++;
					} 
					else{
						cache.misses++;
						System.out.println(" ");
						System.out.println(count+" " + "Cache obtained a miss ");
						String f="";
						if(memory==64) {
							f="000000";
						}
						else if(memory==128) {
							f="0000000";
						}
						else if(memory==256) {
							f="00000000";
						}
						int set_no=CL/k;
						int setbits=(int)(Math.log(set_no)/Math.log(2));
						String j=f.substring(Integer.toBinaryString(arr[i]).length())+ Integer.toBinaryString(arr[i]);
						int offset=(int)(Math.log(B) / Math.log(2));
						//String offset1=j.substring(j.length()-offset,j.length()-1);
						String set=j.substring(j.length()-offset-setbits,j.length()-offset);
						int set1=Integer.parseInt(set,2);
						System.out.println("set no:"+set1);
						if(set1==0 && g==0) {yt[0][count]=arr[i];
						r.add(arr[i]);count0++;g++;}

						else if(set1==0 && g==1){yt[1][count]=arr[i];
						r.add(arr[i]);count0++;count1++;g++;}


						else if(set1==1 && g1==0) {yt[2][count]=arr[i];
						r1.add(arr[i]);count2++;g1++;}

						else if(set1==1 && g1==1) {yt[3][count]=arr[i];
						r1.add(arr[i]);count2++;count3++;g1++;}


						else if(set1==2 && g2==0) {yt[4][count]=arr[i];
						r2.add(arr[i]);count4++;g2++;}
						else if(set1==1 && g2==1){yt[5][count]=arr[i];
						r2.add(arr[i]);count4++;count5++;g2++;}


						else if(set1==3 && g3==0) {yt[4][count]=arr[i];
						r3.add(arr[i]);count4++;g3++;}
						else if(set1==3 && g3==1){yt[5][count]=arr[i];}

						else {
							System.out.println(" ");
							System.out.println(count+" "+"A replacement was carried out ");
							System.out.println("Replaced:"+arr[i]);
							int a;
							if(set1==0) {a=Math.max(count0,count1);
							if(a==count0) {yt[0][count]=arr[i];count0=0;int y1=r.remove(0);
							System.out.println("Removed: "+y1);count1++;r.add(arr[i]);}
							else if(a==count1) {yt[1][count]=arr[i];count1=0;int y1=r.remove(0);
							System.out.println("Removed: "+y1);
							count0++;r.add(arr[i]);}} 
							else if(set1==1) 
							{a=Math.max(count2,count3);if(a==count2) {yt[2][count]=arr[i];count2=0;int y1=r1.remove(0);
							System.out.println("Removed: "+y1);r1.add(arr[i]);count3++;}else if(a==count3) {yt[3][count]=arr[i];count3=0;int y1=r1.remove(0);
							System.out.println("Removed: "+y1);r1.add(arr[i]);count2++;}}
							else if(set1==2) {a=Math.max(count4,count5);
							if(a==count4) {yt[4][count]=arr[i];count4=0;int y1=r2.remove(0);
							System.out.println("Removed: "+y1);r2.add(arr[i]);count5++;}else if(a==count5) {yt[5][count]=arr[i];count5=0;int y1=r2.remove(0);
							System.out.println("Removed: "+y1);r2.add(arr[i]);count4++;}}
							else if(set1==4) {a=Math.max(count6,count7);
							if(a==count6) {yt[6][count]=arr[i];count6=0;int y1=r3.remove(0);
							System.out.println("Removed: "+y1);r3.add(arr[i]);count7++;}else if(a==count7) {yt[7][count]=arr[i];count7=0;int y1=r2.remove(0);
							System.out.println("Removed: "+y1);r3.add(arr[i]);count6++;}}}


					}
					count++;
					System.out.println(" ");

					System.out.println("CACHE HITS:" +" "+ cache.hits);
					System.out.println("CACHE MISSES:" +" "+ cache.misses);
					System.out.println("MEMORY READS:" +" "+ cache.reads);
					System.out.println("MEMORY WRITES:" +" "+ cache.writes);
					System.out.println("CACHE SIZE:" +" "+ cache.cache_size);
					System.out.println("BLOCK SIZE:" +" "+ cache.block_size);
					System.out.println("NUMBER OF LINES:" +" "+ cache.numLines);
				}
				else if(k==4 && CL==8) {

					if (count!=0 && (r.contains(arr[i])||r1.contains(arr[i]))){
						cache.hits++;
						System.out.println(" ");
						System.out.println(count+" "+"Cache obtained a hit ");
						if(r.contains(arr[i])) {count0++;count1++;count3++;count2++;}
						else if(r1.contains(arr[i])) {count4++;count5++;count6++;count7++;}count++;
					} 
					else{
						cache.misses++;
						System.out.println(" ");
						System.out.println(count+" " + "Cache obtained a miss ");
						String f="";
						if(memory==64) {
							f="000000";
						}
						else if(memory==128) {
							f="0000000";
						}
						else if(memory==256) {
							f="00000000";
						}
						int set_no=CL/k;
						int setbits=(int)(Math.log(set_no)/Math.log(2));
						String j=f.substring(Integer.toBinaryString(arr[i]).length())+ Integer.toBinaryString(arr[i]);
						int offset=(int)(Math.log(B) / Math.log(2));
						//String offset1=j.substring(j.length()-offset,j.length()-1);
						String set=j.substring(j.length()-offset-setbits,j.length()-offset);
						int set1=Integer.parseInt(set,2);
						System.out.println("set no:"+set1);
						if(set1==0 && g==0) {yt[0][count]=arr[i];
						r.add(arr[i]);count0++;g++;}else if(set1==0 && g==1){yt[1][count]=arr[i];
						r.add(arr[i]);count0++;count1++;g++;}else if(set1==0 && g==2){yt[2][count]=arr[i];
						r.add(arr[i]);count0++;count1++;count2++;g++;}else if(set1==0 && g==3){yt[3][count]=arr[i];
						r.add(arr[i]);count0++;count1++;count2++;count3++;g++;}
						else if(set1==1 && g1==0) {yt[4][count]=arr[i];r1.add(arr[i]);count4++;g1++;}
						else if(set1==1 && g1==1) {yt[5][count]=arr[i];r1.add(arr[i]);count5++;count4++;g1++;}
						else if(set1==1 && g1==2) {yt[6][count]=arr[i];r1.add(arr[i]);
						count6++;count5++;count4++;g1++;}else if(set1==1 && g1==3) {yt[7][count]=arr[i];r1.add(arr[i]);
						count7++;count6++;count5++;count4++;g1++;}
						else {System.out.println(" ");
						System.out.println(count+" "+"A replacement was carried out ");
						System.out.println("Replaced:"+arr[i]);
						int a=0;
						if(set1==0) {a=Math.max(Math.max(count0,count1),Math.max(count2,count3));
						if(a==count0) {yt[0][count]=arr[i];count0=0;int y1=r.remove(0);
						System.out.println("Removed: "+y1);count1++;count2++;count3++;r.add(arr[i]);}else if(a==count1) {yt[1][count]=arr[i];count1=0;
						int y1=r.remove(0);System.out.println("Removed: "+y1);count0++;count2++;count3++;r.add(arr[i]);}}
						else if(a==count2) {yt[2][count]=arr[i];count2=0;int y1=r.remove(0);
						System.out.println("Removed: "+y1);count1++;count0++;count3++;r.add(arr[i]);}else if(a==count3) {yt[3][count]=arr[i];count3=0;
						int y1=r.remove(0);System.out.println("Removed: "+y1);count0++;count2++;count1++;r.add(arr[i]);}
						else if(set1==1) {{a=Math.max(Math.max(count4,count5),Math.max(count6,count7));
						if(a==count4) {yt[4][count]=arr[i];count4=0;int y1=r1.remove(0);
						System.out.println("Removed: "+y1);count5++;count6++;count7++;r1.add(arr[i]);}else if(a==count5) {yt[5][count]=arr[i];count5=0;
						int y1=r1.remove(0);System.out.println("Removed: "+y1);count4++;count6++;count7++;r1.add(arr[i]);}
						else if(a==count6) {yt[6][count]=arr[i];count6=0;int y1=r1.remove(0);
						System.out.println("Removed: "+y1);count5++;count7++;count4++;r1.add(arr[i]);}else if(a==count7) {yt[7][count]=arr[i];count7=0;
						int y1=r1.remove(0);System.out.println("Removed: "+y1);count4++;count5++;count6++;r1.add(arr[i]);}}
						}
						count++;
						System.out.println(" ");

						System.out.println("CACHE HITS:" +" "+ cache.hits);
						System.out.println("CACHE MISSES:" +" "+ cache.misses);
						System.out.println("MEMORY READS:" +" "+ cache.reads);
						System.out.println("MEMORY WRITES:" +" "+ cache.writes);
						System.out.println("CACHE SIZE:" +" "+ cache.cache_size);
						System.out.println("BLOCK SIZE:" +" "+ cache.block_size);
						System.out.println("NUMBER OF LINES:" +" "+ cache.numLines);
						}}}
				else if(k==4 && CL==16) {
					if (count!=0 && (r.contains(arr[i])||r1.contains(arr[i])||r2.contains(arr[i])||r3.contains(arr[i]))){
						cache.hits++;
						System.out.println(" ");
						System.out.println(count+" "+"Cache obtained a hit ");
						if(r.contains(arr[i])) {count0++;count1++;count3++;count2++;}
						else if(r1.contains(arr[i])) {count4++;count5++;count6++;count7++;}
						else if(r2.contains(arr[i])) {count8++;count9++;count10++;count11++;}
						else if(r3.contains(arr[i])) {count13++;count15++;count12++;count14++;}
						count++;}
					else{
						cache.misses++;
						System.out.println(" ");
						System.out.println(count+" " + "Cache obtained a miss ");
						String f="";
						if(memory==64) {
							f="000000";
						}
						else if(memory==128) {
							f="0000000";
						}
						else if(memory==256) {
							f="00000000";
						}
						int set_no=CL/k;
						int setbits=(int)(Math.log(set_no)/Math.log(2));
						String j=f.substring(Integer.toBinaryString(arr[i]).length())+ Integer.toBinaryString(arr[i]);
						int offset=(int)(Math.log(B) / Math.log(2));
						//String offset1=j.substring(j.length()-offset,j.length()-1);
						String set=j.substring(j.length()-offset-setbits,j.length()-offset);
						int set1=Integer.parseInt(set,2);
						System.out.println("set no:"+set1);
						if(set1==0 && g==0) {yt[0][count]=arr[i];
						r.add(arr[i]);count0++;g++;}else if(set1==0 && g==1){yt[1][count]=arr[i];
						r.add(arr[i]);count0++;count1++;g++;}else if(set1==0 && g==2){yt[2][count]=arr[i];
						r.add(arr[i]);count0++;count1++;count2++;g++;}else if(set1==0 && g==3){yt[3][count]=arr[i];
						r.add(arr[i]);count0++;count1++;count2++;count3++;g++;}
						else if(set1==1 && g1==0) {yt[4][count]=arr[i];r1.add(arr[i]);count4++;g1++;}
						else if(set1==1 && g1==1) {yt[5][count]=arr[i];r1.add(arr[i]);count5++;count4++;g1++;}
						else if(set1==1 && g1==2) {yt[6][count]=arr[i];r1.add(arr[i]);
						count6++;count5++;count4++;g1++;}else if(set1==1 && g1==3) {yt[7][count]=arr[i];r1.add(arr[i]);
						count7++;count6++;count5++;count4++;g1++;}else if(set1==2 && g2==0) {yt[8][count]=arr[i];r2.add(arr[i]);count8++;g2++;}
						else if(set1==2 && g2==1) {yt[9][count]=arr[i];r2.add(arr[i]);count8++;count9++;g2++;}
						else if(set1==2 && g2==2) {yt[10][count]=arr[i];r2.add(arr[i]);
						count10++;count9++;count8++;g2++;}else if(set1==2 && g1==3) {yt[11][count]=arr[i];r2.add(arr[i]);
						count11++;count10++;count9++;count8++;g2++;}
						else if(set1==3 && g3==0) {yt[12][count]=arr[i];r3.add(arr[i]);count12++;g3++;}
						else if(set1==3 && g3==1) {yt[13][count]=arr[i];r3.add(arr[i]);count12++;count13++;g3++;}
						else if(set1==3 && g3==2) {yt[14][count]=arr[i];r3.add(arr[i]);
						count12++;count3++;count4++;g2++;}else if(set1==3 && g3==3) {yt[15][count]=arr[i];r3.add(arr[i]);
						count15++;count13++;count12++;count14++;g2++;}

						else {
							System.out.println(" ");
							System.out.println(count+" "+"A replacement was carried out ");
							System.out.println("Replaced:"+arr[i]);
							int a=0;
							if(set1==0) {a=Math.max(Math.max(count0,count1),Math.max(count2,count3));
							if(a==count0) yt[0][count]=arr[i];count0=0;int y1=r.remove(0);
							System.out.println("Removed: "+y1);count1++;count2++;count3++;r.add(arr[i]);}else if(a==count1) {yt[1][count]=arr[i];count1=0;
							int y1=r.remove(0);System.out.println("Removed: "+y1);count0++;count2++;count3++;r.add(arr[i]);}
							else if(a==count2) {yt[2][count]=arr[i];count2=0;int y1=r.remove(0);
							System.out.println("Removed: "+y1);count1++;count0++;count3++;r.add(arr[i]);}else if(a==count3) {yt[3][count]=arr[i];count3=0;
							int y1=r.remove(0);System.out.println("Removed: "+y1);count0++;count2++;count1++;r.add(arr[i]);}
							else if(set1==1) {{a=Math.max(Math.max(count4,count5),Math.max(count6,count7));
							if(a==count4) {yt[4][count]=arr[i];count4=0;int y1=r1.remove(0);
							System.out.println("Removed: "+y1);count5++;count6++;count7++;r1.add(arr[i]);}else if(a==count5) {yt[5][count]=arr[i];count5=0;
							int y1=r1.remove(0);System.out.println("Removed: "+y1);count4++;count6++;count7++;r1.add(arr[i]);}
							else if(a==count6) {yt[6][count]=arr[i];count6=0;int y1=r1.remove(0);
							System.out.println("Removed: "+y1);count5++;count7++;count4++;r1.add(arr[i]);}else if(a==count7) {yt[7][count]=arr[i];count7=0;
							int y1=r1.remove(0);System.out.println("Removed: "+y1);count4++;count5++;count6++;r1.add(arr[i]);}}}

							else if(set1==2) {{a=Math.max(Math.max(count9,count8),Math.max(count10,count11));
							if(a==count8) {yt[8][count]=arr[i];count8=0;int y1=r2.remove(0);
							System.out.println("Removed: "+y1);count9++;count10++;count11++;r2.add(arr[i]);}else if(a==count9) {yt[9][count]=arr[i];count9=0;
							int y1=r2.remove(0);System.out.println("Removed: "+y1);count10++;count11++;count8++;r2.add(arr[i]);}
							else if(a==count10) {yt[10][count]=arr[i];count10=0;int y1=r1.remove(0);
							System.out.println("Removed: "+y1);count11++;count9++;count8++;r2.add(arr[i]);}else if(a==count11) {yt[11][count]=arr[i];count11=0;
							int y1=r2.remove(0);System.out.println("Removed: "+y1);count10++;count9++;count8++;r2.add(arr[i]);}}}


							else if(set1==3) {{a=Math.max(Math.max(count12,count13),Math.max(count14,count15));
							if(a==count12) {yt[12][count]=arr[i];count12=0;int y1=r3.remove(0);
							System.out.println("Removed: "+y1);count13++;count14++;count15++;r3.add(arr[i]);}else if(a==count13) {yt[13][count]=arr[i];count13=0;
							int y1=r3.remove(0);System.out.println("Removed: "+y1);count14++;count12++;count15++;r3.add(arr[i]);}}}
							else if(a==count14) {yt[14][count]=arr[i];count14=0;int y1=r3.remove(0);
							System.out.println("Removed: "+y1);count15++;count12++;count13++;r3.add(arr[i]);}else if(a==count15) {yt[15][count]=arr[i];count15=0;
							int y1=r3.remove(0);System.out.println("Removed: "+y1);count14++;count13++;count12++;r3.add(arr[i]);}}}
					
					count++;
					System.out.println(" ");

					System.out.println("CACHE HITS:" +" "+ cache.hits);
					System.out.println("CACHE MISSES:" +" "+ cache.misses);
					System.out.println("MEMORY READS:" +" "+ cache.reads);
					System.out.println("MEMORY WRITES:" +" "+ cache.writes);
					System.out.println("CACHE SIZE:" +" "+ cache.cache_size);
					System.out.println("BLOCK SIZE:" +" "+ cache.block_size);
					System.out.println("NUMBER OF LINES:" +" "+ cache.numLines);
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

	public static void main(String[] args) throws IOException {
		Reader.init(System.in);
		int S=Reader.nextInt();
		int CL=Reader.nextInt();
		int B=Reader.nextInt();
		int K=Reader.nextInt();
		int flag=Reader.nextInt();
		setcache obj=new setcache();
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

			obj.L1find(K,B,L1cache,memerq,mem1,memrq1, L2cache,CL);
			for(int i=0;i<y1.size();i++) {
				System.out.println(y1.get(i));
			}
			obj.destroyCache(L1cache);
			obj.destroyCache(L2cache);

		}
	}
}

