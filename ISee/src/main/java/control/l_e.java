    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.opencsv.CSVReader;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * Lectura escritura de todo
 * @author edson
 */
public class l_e {

    public static JLabel nombrearchivo;
    public static List<String[]> lista=new ArrayList();
    public static List<String[]> lista2;
    public static List<String[]> lista3;
    public static String[] titulo;
    public static int i_s;
    public static int v_s;

    public static DefaultListModel<String> mver,mignorar;
    public static JList<String> ignorar;
    public static JList<String> ver;
    public static File leer_archivo()
    {
        //////
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos csv","csv");
        //////
        
        File file=null;
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        
        ////////
        jfc.setAcceptAllFileFilterUsed(false);
        jfc.addChoosableFileFilter(filter);
        ////////
        
        jfc.setDialogTitle("Selecciona un archivo");
	jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                

		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			if (jfc.getSelectedFile().isFile()) {
                            file=jfc.getSelectedFile();
                            String nombre=file.getName();
                            int max=nombre.length();
                            //max--;
                            max=(max>15) ? 15 : max;
                            System.out.println("Maximo:"+max);
                            
                            nombrearchivo.setText(nombre.substring(0,max));
			    JOptionPane.showMessageDialog(null,"Archivo seleccionado: " + jfc.getSelectedFile());
			}
		}
        return file;
    }
    
    
    public static void leer_csv()
    {
        
        
        try{        
        Path ruta=leer_archivo().toPath();
        Reader reader = Files.newBufferedReader(ruta);    
        CSVReader csvReader = new CSVReader(reader);
        
        
        titulo= csvReader.readNext();
        
        //l1=new DefaultListModel<>();
        //l2=new DefaultListModel<>();
        
        Arrays.stream(titulo).forEach(x->
        {
            mver.addElement(x);
            
            //l2.addElement(Double.parseDouble(x));
        });
        
        String x[];
        
        while((x=csvReader.readNext())!=null)
        {
            lista.add(x);
        }
        
        lista2=new ArrayList();
        lista.forEach(lista2::add);
        
        }
        catch(IOException e)
        {
        JOptionPane.showMessageDialog(null, "Error en lectura de archivo");
        }
        catch(NullPointerException e)
        {
            
        }
        
        //ver.setSelectionModel((ListSelectionModel) l1);
                
    }
    
    
    public static void ver_ignorar()
    {
        if(!ignorar.isSelectionEmpty()){
            lista3=new ArrayList();
            lista2.stream().forEach(
                    x->
                    {
                        String[] x_=new String[x.length-1];
                        int j=0;
                        for(int i=0;i<x.length;i++)
                        {
                            if(i==i_s)
                                continue;
                            j++;
                            x_[j]=x[i];
                            
                        }
                        
                        lista3.add(x);
                    }
            );
                    
        }
        lista2=lista3;
        var v_lista=ver.getSelectionModel();
        v_lista.removeIndexInterval(i_s, i_s);
    }
    
    
}

