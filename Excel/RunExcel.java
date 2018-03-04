import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;

public class RunExcel {
	public static void main(String[]args) {
		JFileChooser f = new JFileChooser();
		
		f.setFileSelectionMode(JFileChooser.FILES_ONLY);
		f.setAcceptAllFileFilterUsed(false);
		f.setFileFilter(new FileNameExtensionFilter("Excel file","xlsx","xls"));
		int returnValue = f.showOpenDialog(null);
		
		if(returnValue == JFileChooser.APPROVE_OPTION) {
			 File selectedFile = f.getSelectedFile();
			
			Excel a = new Excel(selectedFile.getAbsolutePath());
			a.write();
			a.read();
			
		}else {
			JOptionPane.showMessageDialog(null, "你沒選擇檔案", "錯誤", JOptionPane.WARNING_MESSAGE);
		}
	}
}
