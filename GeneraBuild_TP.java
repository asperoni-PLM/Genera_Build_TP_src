import javax.swing.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.*;


public class GeneraBuild_TP extends JFrame{
//Test modifica 2
//	public String sBuildsPath = "E:/SAFILO_BUILDS/";
	public String sBuildsPath = "C:/Temp/";
	
	public static void main (String[] arguments) throws IOException {
		new GeneraBuild_TP();
		System.runFinalization();
	}

	protected void finalize() throws Throwable {
		super.finalize();
	}
	
	GeneraBuild_TP() throws IOException {
		//JTextArea jtaBuildName = new JTextArea();

		Font labelFont = new Font("TimesRoman", Font.BOLD, 15);
		Font textFont = new Font("TimesRoman", Font.PLAIN, 15);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
//		System.out.println("-->width<--"+width);
//		System.out.println("-->height<--"+height);
		if (width > 2400) {
			labelFont = new Font("TimesRoman", Font.BOLD, 45);
			textFont = new Font("TimesRoman", Font.PLAIN, 45);
		}
//		String sInputFile = sBuildsPath+"buildCounter.txt";
		final String sBuildPrefix = "V6R2013x_";
		final String sBuildMiddle = "_PLMSYST_";

//	    FileReader fdIn = new FileReader(sInputFile);
//	    BufferedReader bufReader = new BufferedReader(fdIn);
//	    String sPreviuousNuildNumber ="";
//	    Integer iBuildNumber = 0;
//		while ((sPreviuousNuildNumber = bufReader.readLine()) != null) {
//			Integer iPrevBuildNumber = new Integer(sPreviuousNuildNumber);
//			iBuildNumber = iPrevBuildNumber+1;
//		}
//
//
//	    bufReader.close();

	    String sPreviuousNuildNumber ="";
	    Integer iBuildNumber = 0;
	    URL counterFromSite = new URL("http://www.cvsperoni.it/Tmp/buildCounter.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(counterFromSite.openStream()));

        String inputLine;
        while ((sPreviuousNuildNumber = in.readLine()) != null) {
			Integer iPrevBuildNumber = new Integer(sPreviuousNuildNumber);
			iBuildNumber = iPrevBuildNumber+1;
		}
        
	    
	    
		JLabel jtaBuildNumberLabel = new JLabel("Build Number       ");
		jtaBuildNumberLabel.setFont(labelFont);
		JLabel jtaBuildNumberLabelDot_1 = new JLabel(".");
		jtaBuildNumberLabelDot_1.setFont(labelFont);
		final JTextField jtaBuildNumber_1;
		if (iBuildNumber < 10) {
			jtaBuildNumber_1 = new JTextField("0"+iBuildNumber,3);
		} else {
			jtaBuildNumber_1 = new JTextField(""+iBuildNumber,3);
		}
		jtaBuildNumber_1.setFont(textFont);
		final JTextField jtaBuildNumber_2 = new JTextField("0000",3);
		jtaBuildNumber_2.setFont(textFont);
		JPanel jPanel_BN = new JPanel(new FlowLayout(FlowLayout.LEFT));

		jPanel_BN.add(jtaBuildNumberLabel);
		jPanel_BN.add(jtaBuildNumber_1);
		jPanel_BN.add(jtaBuildNumberLabelDot_1);
		jPanel_BN.add(jtaBuildNumber_2);
		

		Calendar dDate = Calendar.getInstance();
		int iMese = dDate.get(Calendar.MONTH)+1;
		String sMese = ""+iMese;
		if (iMese < 10) {
			sMese="0"+iMese;
		}
		int iGiorno = dDate.get(Calendar.DATE);
		String sGiorno = ""+iGiorno;
		if (iGiorno < 10) {
			sGiorno="0"+iGiorno;
		}

		JLabel jtaBuildDateLabel = new JLabel("Build Date             ");
		jtaBuildDateLabel.setFont(labelFont);
		final JTextField jtaDateNumber = new JTextField("2017"+sMese+sGiorno,10);
		jtaDateNumber.setFont(textFont);
		//textFieldPanel.add(jtaBuildDateLabel);
		JPanel jPanel_BDate = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jPanel_BDate.add(jtaBuildDateLabel);
		jPanel_BDate.add(jtaDateNumber);

		JLabel jtaBuildOwnerLabel = new JLabel("Build Owner");
		jtaBuildOwnerLabel.setFont(labelFont);
	

		final JComboBox jtaBuildOwner = new JComboBox();
		JPanel jPanel_BOwner = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jtaBuildOwner.setFont(textFont);
		jtaBuildOwner.addItem("SELECT OWNER"); // MB
		jtaBuildOwner.addItem("Airoldi"); // AU
		jtaBuildOwner.addItem("Bobel"); // MB
		jtaBuildOwner.addItem("Buzi"); // AB
		jtaBuildOwner.addItem("Caprara"); // GC
		jtaBuildOwner.addItem("Grasso"); // VG
		jtaBuildOwner.addItem("Hysaj"); // IH
		jtaBuildOwner.addItem("Picco"); // EP
		jtaBuildOwner.addItem("Speroni"); // AS
		jPanel_BOwner.add(jtaBuildOwnerLabel);
		jPanel_BOwner.add(jtaBuildOwner);

		
		JLabel jtaBuildDescLabel = new JLabel("Build Description");
		jtaBuildDescLabel.setFont(labelFont);
		final JTextField jtaBuildDesc = new JTextField("",20);
		jtaBuildDesc.setFont(textFont);
		JPanel jPanel_BDesc = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jPanel_BDesc.add(jtaBuildDescLabel);
		jPanel_BDesc.add(jtaBuildDesc);

		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		JLabel jtaBuildFolderLabel = new JLabel("Build Folder");
		jtaBuildFolderLabel.setFont(labelFont);
		final JTextField jtaBuildFolder = new JTextField("C:\\Temp\\",20);
		jtaBuildFolder.setFont(textFont);
		JPanel jPanel_BFolder = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jPanel_BFolder.add(jtaBuildFolderLabel);
		jPanel_BFolder.add(jtaBuildFolder);

		
		JButton jbuttonChooseDirBuild = new JButton("Choose Dir");
		jbuttonChooseDirBuild.setFont(textFont);
		jPanel_BFolder.add(jbuttonChooseDirBuild);
		jbuttonChooseDirBuild.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFileChooser j = new JFileChooser("C:/Temp/");
				j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = j.showOpenDialog(getParent());
				jtaBuildFolder.setText(j.getSelectedFile().toString());
				sBuildsPath=j.getSelectedFile().toString();
//				Integer opt = j.showSaveDialog(this);
			}
		});
		
//		JFileChooser j = new JFileChooser();
//		j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//		Integer opt = j.showSaveDialog(this);

		
		JPanel jPanel_Button = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton jbuttonCreateBuild = new JButton("Genera");
		jbuttonCreateBuild.setFont(textFont);
		jbuttonCreateBuild.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
//				File file_Orig = new File(sBuildsPath+"TEMPLATE");
				String sBuildNumber_1 = jtaBuildNumber_1.getText();
				if (sBuildNumber_1.length() == 1)  sBuildNumber_1="0"+sBuildNumber_1;
				String sBuildNumber_2 = jtaBuildNumber_2.getText();
				String sBuildNumber = sBuildNumber_1+"."+sBuildNumber_2;
				String sBuildDateNumber = jtaDateNumber.getText();
				String sBuildDesc = jtaBuildDesc.getText();
				String sOwner = (String)jtaBuildOwner.getSelectedItem();
				if (sOwner.equals("SELECT OWNER")) {
					JOptionPane.showMessageDialog(null, "Selezionare Owner");
					return;
				} else {
					if (sOwner.equals("Bobel")) {
						sOwner = "MB";
					} else if (sOwner.equals("Caprara")) {
						sOwner = "GC";
					} else if (sOwner.equals("Grasso")) {
						sOwner = "VG";
					} else if (sOwner.equals("Picco")) {
						sOwner = "EP";
					} else if (sOwner.equals("Airoldi")) {
						sOwner = "PA";
					} else if (sOwner.equals("Hysaj")) {
						sOwner = "IH";
					} else if (sOwner.equals("Speroni")) {
						sOwner = "AS";
					} else if (sOwner.equals("Buzi")) {
						sOwner = "AB";
					}
				}
				if (sBuildDesc.equals("")) {
					JOptionPane.showMessageDialog(null, "Compilare il campo Descrizione");
					return;
				}
				try {
					//V6R2014x_FP1514_nn.mmm_EYE_PLMSYST_DescBreve_201501xx
					//nn: numero update
				    //mmm: numero "pacchetto"
					//V6R2014x_FP1514_03.0001_EYE_PLMSYST_ColorsManagemen_20151009
					
					File file_Dest = new File(sBuildsPath+sBuildPrefix+sBuildNumber+sBuildMiddle+sBuildDesc+"_"+sOwner+"_"+sBuildDateNumber);
					//generateFolder(sBuildsPath,file_Dest);
					//copyFolder(file_Orig,file_Dest);
					generateFolder(file_Dest);
				    java.io.File exportFile = new java.io.File(sBuildsPath+sBuildPrefix+sBuildNumber+sBuildMiddle+sBuildDesc+"_"+sOwner+"_"+sBuildDateNumber+"/ToExport.txt");
				    exportFile.createNewFile();
				    BufferedWriter bufLogWriterExport = new BufferedWriter(new FileWriter(exportFile));

				    String strExportProgram = "#export program XXX into file "+sBuildsPath+sBuildPrefix+sBuildNumber+sBuildMiddle+sBuildDesc+"_"+sOwner+"_"+sBuildDateNumber+"/exports/program_XXX.exp;";
				    String strExportForm = "#export form YYY into file "+sBuildsPath+sBuildPrefix+sBuildNumber+sBuildMiddle+sBuildDesc+"_"+sOwner+"_"+sBuildDateNumber+"/exports/form_YYY.exp;";
				    String strExportTable = "#export table ZZZ into file "+sBuildsPath+sBuildPrefix+sBuildNumber+sBuildMiddle+sBuildDesc+"_"+sOwner+"_"+sBuildDateNumber+"/exports/table_ZZZ.exp;";

				    bufLogWriterExport.write(strExportProgram);
				    bufLogWriterExport.newLine();
				    bufLogWriterExport.newLine();
				    bufLogWriterExport.flush();
				    bufLogWriterExport.write(strExportForm);
				    bufLogWriterExport.newLine();
				    bufLogWriterExport.newLine();
				    bufLogWriterExport.flush();
				    bufLogWriterExport.write(strExportTable);
				    bufLogWriterExport.newLine();
				    bufLogWriterExport.newLine();
				    bufLogWriterExport.flush();
				    bufLogWriterExport.close();

				    java.io.File scriptFile = new java.io.File(sBuildsPath+sBuildPrefix+sBuildNumber+sBuildMiddle+sBuildDesc+"_"+sOwner+"_"+sBuildDateNumber+"/mql/Script.mql");
				    scriptFile.createNewFile();
				    BufferedWriter bufLogWriterScript = new BufferedWriter(new FileWriter(scriptFile));


				    String strImportProgram = "#import program XXX overwrite from file ../exports/program_XXX.exp;";
				    String strImportForm = "#import form YYY overwrite from file ../exports/form_YYY.exp;";
				    String strImportTable = "#import table ZZZ overwrite from file ../exports/table_ZZZ.exp;";

				    bufLogWriterScript.write(strImportProgram);
				    bufLogWriterScript.newLine();
				    bufLogWriterScript.newLine();
				    bufLogWriterScript.flush();
				    bufLogWriterScript.write(strImportForm);
				    bufLogWriterScript.newLine();
				    bufLogWriterScript.newLine();
				    bufLogWriterScript.flush();
				    bufLogWriterScript.write(strImportTable);
				    bufLogWriterScript.newLine();
				    bufLogWriterScript.newLine();
				    bufLogWriterScript.flush();
				    bufLogWriterScript.close();
//				    
//
//				    try {
//					    URL                url; 
//					    
//					    HttpURLConnection      urlConn; 
//			            DataOutputStream   dos; 
//			            DataInputStream    dis;
//	
//			            url = new URL("http://www.cvsperoni.it/Tmp/buildCounter.txt"); 
//			            urlConn = (HttpURLConnection) url.openConnection();
//
//			            urlConn.setDoInput(true); 
//			            urlConn.setDoOutput(true); 
//			            urlConn.setUseCaches(false);
//			            urlConn.setRequestMethod("GET");
//			            urlConn.setRequestProperty ("Content-Type", "application/txt");
//			            System.out.println("urlConn ___________"+urlConn+"___________");
//	
//			            OutputStream os = urlConn.getOutputStream();
//			            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
//			            writer.write("antani");
//			            writer.flush();
//			            writer.close();
//			            os.close();
//		            
//			            urlConn.connect();
//			            int responseCode = urlConn.getResponseCode();
//			            InputStream is = urlConn.getInputStream();
//			            System.out.println("is ___________"+is.read()+"___________");
//			            
//				    } catch (MalformedURLException mue) { 
//			            System.out.println("mue ___________"+mue.toString()+"___________");
//				    } 
//				    catch (IOException ioe) { 
//			            System.out.println("ioe ___________"+ioe.toString()+"___________");
//				    }
//            
//		            
					System.exit(0);
				} catch (IOException Exc) {
					System.exit(0);
				}
			}
		});
		jPanel_Button.add(jbuttonCreateBuild);

		getContentPane().add(jPanel_BN);
		getContentPane().add(jPanel_BDate);
		getContentPane().add(jPanel_BOwner);
		getContentPane().add(jPanel_BDesc);
		getContentPane().add(jPanel_BFolder);
		getContentPane().add(jPanel_Button);
		if (width > 2400) {
			this.setSize(1300, 700);
		} else {
			this.setSize(550, 350);
		}
		
		this.setTitle("Generazione automatica build");
		this.setResizable(true);
		setVisible(true);
	}
	
	public static void copyFolder(File file_Orig, File file_Dest) 
	throws IOException {
		if (file_Orig.isDirectory()) {
			if (!file_Dest.exists()) {
				file_Dest.mkdir();
			}
			String files[] = file_Orig.list();
			for (String file : files) {
				File srcFile = new File(file_Orig, file);
				File destFile = new File(file_Dest, file);
				copyFolder(srcFile, destFile);
			}
		} else {
			InputStream in = new FileInputStream(file_Orig);
			OutputStream out = new FileOutputStream(file_Dest);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer,0,length);
			}
			in.close();
			out.close();
		}
	}
	
	public static void generateFolder(File file_Dest) 
	throws IOException {
		file_Dest.mkdir();
		new File(file_Dest.getPath().toString()+"\\exports").mkdir();
		new File(file_Dest.getPath().toString()+"\\mql").mkdir();
		new File(file_Dest.getPath().toString()+"\\Spinner").mkdir();
		new File(file_Dest.getPath().toString()+"\\web").mkdir();
	}

}
