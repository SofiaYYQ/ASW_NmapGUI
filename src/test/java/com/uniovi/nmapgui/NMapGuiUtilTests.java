package com.uniovi.nmapgui;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.xml.transform.TransformerException;

import org.junit.Test;

import com.uniovi.nmapgui.util.Filefinder;
import com.uniovi.nmapgui.util.TransInfoHtml;

public class NMapGuiUtilTests {

	@Test
	public void fileFinderTest() {
		try {
			Files.write(Paths.get(System.getProperty("java.io.tmpdir")+"/test.xml")
				, Arrays.asList("Test"), Charset.forName("UTF-8"));		
			InputStream file = new Filefinder().find("test");			
			
			BufferedReader r = new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8));
			String str = null; 
			str = r.readLine();
			
			assertEquals("Test", str);
			Files.delete(Paths.get(System.getProperty("java.io.tmpdir")+"/test.xml"));

			
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				Files.delete(Paths.get(System.getProperty("java.io.tmpdir")+"/test.xml"));
			} catch (IOException e1) {}
			fail();
		}
	}
	
	@Test
	public void transInfoHTMLTest(){
		try {
			Constructor<TransInfoHtml> c = TransInfoHtml.class.getDeclaredConstructor();
			c.setAccessible(true);		
			c.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		String testStr= "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<!DOCTYPE nmaprun>\r\n<?xml-stylesheet href=\"file:///usr/bin/../share/nmap/nmap.xsl\" type=\"text/xsl\"?>\r\n<!-- Nmap 7.40 scan initiated Tue Jul 11 03:29:13 2017 as: nmap -O -oX /root/Documents/workspace-sts-3.8.4.RELEASE/NMapGUI/src/main/resources/static/temp/nmap-scan_2017-07-11_03-29-12.xml  google.es -->\r\n<nmaprun scanner=\"nmap\" args=\"nmap -O -oX /root/Documents/workspace-sts-3.8.4.RELEASE/NMapGUI/src/main/resources/static/temp/nmap-scan_2017-07-11_03-29-12.xml  google.es\" start=\"1499758153\" startstr=\"Tue Jul 11 03:29:13 2017\" version=\"7.40\" xmloutputversion=\"1.04\">\r\n<scaninfo type=\"syn\" protocol=\"tcp\" numservices=\"1000\" services=\"1,3-4,6-7,9,13,17,19-26,30,32-33,37,42-43,49,53,70,79-85,88-90,99-100,106,109-111,113,119,125,135,139,143-144,146,161,163,179,199,211-212,222,254-256,259,264,280,301,306,311,340,366,389,406-407,416-417,425,427,443-445,458,464-465,481,497,500,512-515,524,541,543-545,548,554-555,563,587,593,616-617,625,631,636,646,648,666-668,683,687,691,700,705,711,714,720,722,726,749,765,777,783,787,800-801,808,843,873,880,888,898,900-903,911-912,981,987,990,992-993,995,999-1002,1007,1009-1011,1021-1100,1102,1104-1108,1110-1114,1117,1119,1121-1124,1126,1130-1132,1137-1138,1141,1145,1147-1149,1151-1152,1154,1163-1166,1169,1174-1175,1183,1185-1187,1192,1198-1199,1201,1213,1216-1218,1233-1234,1236,1244,1247-1248,1259,1271-1272,1277,1287,1296,1300-1301,1309-1311,1322,1328,1334,1352,1417,1433-1434,1443,1455,1461,1494,1500-1501,1503,1521,1524,1533,1556,1580,1583,1594,1600,1641,1658,1666,1687-1688,1700,1717-1721,1723,1755,1761,1782-1783,1801,1805,1812,1839-1840,1862-1864,1875,1900,1914,1935,1947,1971-1972,1974,1984,1998-2010,2013,2020-2022,2030,2033-2035,2038,2040-2043,2045-2049,2065,2068,2099-2100,2103,2105-2107,2111,2119,2121,2126,2135,2144,2160-2161,2170,2179,2190-2191,2196,2200,2222,2251,2260,2288,2301,2323,2366,2381-2383,2393-2394,2399,2401,2492,2500,2522,2525,2557,2601-2602,2604-2605,2607-2608,2638,2701-2702,2710,2717-2718,2725,2800,2809,2811,2869,2875,2909-2910,2920,2967-2968,2998,3000-3001,3003,3005-3007,3011,3013,3017,3030-3031,3052,3071,3077,3128,3168,3211,3221,3260-3261,3268-3269,3283,3300-3301,3306,3322-3325,3333,3351,3367,3369-3372,3389-3390,3404,3476,3493,3517,3527,3546,3551,3580,3659,3689-3690,3703,3737,3766,3784,3800-3801,3809,3814,3826-3828,3851,3869,3871,3878,3880,3889,3905,3914,3918,3920,3945,3971,3986,3995,3998,4000-4006,4045,4111,4125-4126,4129,4224,4242,4279,4321,4343,4443-4446,4449,4550,4567,4662,4848,4899-4900,4998,5000-5004,5009,5030,5033,5050-5051,5054,5060-5061,5080,5087,5100-5102,5120,5190,5200,5214,5221-5222,5225-5226,5269,5280,5298,5357,5405,5414,5431-5432,5440,5500,5510,5544,5550,5555,5560,5566,5631,5633,5666,5678-5679,5718,5730,5800-5802,5810-5811,5815,5822,5825,5850,5859,5862,5877,5900-5904,5906-5907,5910-5911,5915,5922,5925,5950,5952,5959-5963,5987-5989,5998-6007,6009,6025,6059,6100-6101,6106,6112,6123,6129,6156,6346,6389,6502,6510,6543,6547,6565-6567,6580,6646,6666-6669,6689,6692,6699,6779,6788-6789,6792,6839,6881,6901,6969,7000-7002,7004,7007,7019,7025,7070,7100,7103,7106,7200-7201,7402,7435,7443,7496,7512,7625,7627,7676,7741,7777-7778,7800,7911,7920-7921,7937-7938,7999-8002,8007-8011,8021-8022,8031,8042,8045,8080-8090,8093,8099-8100,8180-8181,8192-8194,8200,8222,8254,8290-8292,8300,8333,8383,8400,8402,8443,8500,8600,8649,8651-8652,8654,8701,8800,8873,8888,8899,8994,9000-9003,9009-9011,9040,9050,9071,9080-9081,9090-9091,9099-9103,9110-9111,9200,9207,9220,9290,9415,9418,9485,9500,9502-9503,9535,9575,9593-9595,9618,9666,9876-9878,9898,9900,9917,9929,9943-9944,9968,9998-10004,10009-10010,10012,10024-10025,10082,10180,10215,10243,10566,10616-10617,10621,10626,10628-10629,10778,11110-11111,11967,12000,12174,12265,12345,13456,13722,13782-13783,14000,14238,14441-14442,15000,15002-15004,15660,15742,16000-16001,16012,16016,16018,16080,16113,16992-16993,17877,17988,18040,18101,18988,19101,19283,19315,19350,19780,19801,19842,20000,20005,20031,20221-20222,20828,21571,22939,23502,24444,24800,25734-25735,26214,27000,27352-27353,27355-27356,27715,28201,30000,30718,30951,31038,31337,32768-32785,33354,33899,34571-34573,35500,38292,40193,40911,41511,42510,44176,44442-44443,44501,45100,48080,49152-49161,49163,49165,49167,49175-49176,49400,49999-50003,50006,50300,50389,50500,50636,50800,51103,51493,52673,52822,52848,52869,54045,54328,55055-55056,55555,55600,56737-56738,57294,57797,58080,60020,60443,61532,61900,62078,63331,64623,64680,65000,65129,65389\"/>\r\n<verbose level=\"0\"/>\r\n<debugging level=\"0\"/>\r\n<host starttime=\"1499758153\" endtime=\"1499758162\"><status state=\"up\" reason=\"echo-reply\" reason_ttl=\"53\"/>\r\n<address addr=\"216.58.210.3\" addrtype=\"ipv4\"/>\r\n<hostnames>\r\n<hostname name=\"google.es\" type=\"user\"/>\r\n<hostname name=\"fra16s07-in-f3.1e100.net\" type=\"PTR\"/>\r\n</hostnames>\r\n<ports><extraports state=\"filtered\" count=\"986\">\r\n<extrareasons reason=\"no-responses\" count=\"986\"/>\r\n</extraports>\r\n<port protocol=\"tcp\" portid=\"80\"><state state=\"open\" reason=\"syn-ack\" reason_ttl=\"53\"/><service name=\"http\" method=\"table\" conf=\"3\"/></port>\r\n<port protocol=\"tcp\" portid=\"443\"><state state=\"open\" reason=\"syn-ack\" reason_ttl=\"53\"/><service name=\"https\" method=\"table\" conf=\"3\"/></port>\r\n<port protocol=\"tcp\" portid=\"1720\"><state state=\"open\" reason=\"syn-ack\" reason_ttl=\"255\"/><service name=\"h323q931\" method=\"table\" conf=\"3\"/></port>\r\n<port protocol=\"tcp\" portid=\"6000\"><state state=\"closed\" reason=\"reset\" reason_ttl=\"128\"/><service name=\"X11\" method=\"table\" conf=\"3\"/></port>\r\n<port protocol=\"tcp\" portid=\"6001\"><state state=\"closed\" reason=\"reset\" reason_ttl=\"128\"/><service name=\"X11:1\" method=\"table\" conf=\"3\"/></port>\r\n<port protocol=\"tcp\" portid=\"6002\"><state state=\"closed\" reason=\"reset\" reason_ttl=\"128\"/><service name=\"X11:2\" method=\"table\" conf=\"3\"/></port>\r\n<port protocol=\"tcp\" portid=\"6003\"><state state=\"closed\" reason=\"reset\" reason_ttl=\"128\"/><service name=\"X11:3\" method=\"table\" conf=\"3\"/></port>\r\n<port protocol=\"tcp\" portid=\"6004\"><state state=\"closed\" reason=\"reset\" reason_ttl=\"128\"/><service name=\"X11:4\" method=\"table\" conf=\"3\"/></port>\r\n<port protocol=\"tcp\" portid=\"6005\"><state state=\"closed\" reason=\"reset\" reason_ttl=\"128\"/><service name=\"X11:5\" method=\"table\" conf=\"3\"/></port>\r\n<port protocol=\"tcp\" portid=\"6006\"><state state=\"closed\" reason=\"reset\" reason_ttl=\"128\"/><service name=\"X11:6\" method=\"table\" conf=\"3\"/></port>\r\n<port protocol=\"tcp\" portid=\"6007\"><state state=\"closed\" reason=\"reset\" reason_ttl=\"128\"/><service name=\"X11:7\" method=\"table\" conf=\"3\"/></port>\r\n<port protocol=\"tcp\" portid=\"6009\"><state state=\"closed\" reason=\"reset\" reason_ttl=\"128\"/><service name=\"X11:9\" method=\"table\" conf=\"3\"/></port>\r\n<port protocol=\"tcp\" portid=\"6025\"><state state=\"closed\" reason=\"reset\" reason_ttl=\"128\"/><service name=\"x11\" method=\"table\" conf=\"3\"/></port>\r\n<port protocol=\"tcp\" portid=\"6059\"><state state=\"closed\" reason=\"reset\" reason_ttl=\"128\"/><service name=\"X11:59\" method=\"table\" conf=\"3\"/></port>\r\n</ports>\r\n<os><portused state=\"open\" proto=\"tcp\" portid=\"80\"/>\r\n<portused state=\"closed\" proto=\"tcp\" portid=\"6000\"/>\r\n</os>\r\n<uptime seconds=\"2\" lastboot=\"Tue Jul 11 03:29:20 2017\"/>\r\n<tcpsequence index=\"259\" difficulty=\"Good luck!\" values=\"3E8AA3E3,F81B47F8,E666FB4D,1D085B06,EC951EE7,7BFFFDAA\"/>\r\n<ipidsequence class=\"Randomized\" values=\"249,D2F,3547,B8CB,30C0,B94A\"/>\r\n<tcptssequence class=\"other\" values=\"3E5E3FD6,8344C96E,868BDC5A,436DC5C7,889519AA,436DC690\"/>\r\n<times srtt=\"27226\" rttvar=\"21798\" to=\"114418\"/>\r\n</host>\r\n<runstats><finished time=\"1499758162\" timestr=\"Tue Jul 11 03:29:22 2017\" elapsed=\"9.43\" summary=\"Nmap done at Tue Jul 11 03:29:22 2017; 1 IP address (1 host up) scanned in 9.43 seconds\" exit=\"success\"/><hosts up=\"1\" down=\"0\" total=\"1\"/>\r\n</runstats>\r\n</nmaprun>";
		try{
			String testOut = TransInfoHtml.transformToHtml(testStr);
			testOut.contains("Nmap 7.40 scan initiated Tue Jul 11 03:29:13 2017 as: nmap -O -oX /root/Documents/workspace-sts-3.8.4.RELEASE/NMapGUI/src/main/resources/static/temp/nmap-scan_2017-07-11_03-29-12.xml  google.es -->\r\n<nmaprun scanner=\"nmap\" args=\"nmap -O -oX /root/Documents/workspace-sts-3.8.4.RELEASE/NMapGUI/src/main/resources/static/temp/nmap-scan_2017-07-11_03-29-12.xml  google.es");
		}catch (TransformerException t) {
			fail();
		}
		
		try{
			testStr = "PRUEBA EXCEPCION";
			TransInfoHtml.transformToHtml(testStr);
			fail();
		}catch (TransformerException t) {}

	}
	

}
