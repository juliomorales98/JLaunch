import java.io.File;
import java.io.IOException ;
import java.io.BufferedReader ;
import java.io.InputStream ;
import java.io.InputStreamReader ;
import javax.swing.* ;
import java.awt.* ;
import java.awt.event.* ;

public class Main{
    public static String workPath = "" ;
    public static void main( String[] args ){
        workPath = "A:" + File.separatorChar + "Scripts"  ;
        String[] content = getFolderContent( workPath ) ;
        Point mousePoint = MouseInfo.getPointerInfo().getLocation() ;
        createWindow( content, mousePoint ) ;
    }
    private static String[] getFolderContent( String _path ){
        File dir = new File( _path ) ;

        if( !dir.exists() || !dir.isDirectory() ){
            return null ;
        }

        return dir.list() ;
    }
    private static void createWindow( String[] _content, Point _mousePoint ){
        JFrame frame = new JFrame( "Cursor" ) ;
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ) ;
        int posY = 0 ;
        for( String s : _content ){
            JButton aux = new JButton( s ) ;
            aux.setBounds( 0, posY, 300, 30 ) ;
            aux.addActionListener(
                    new ActionListener(){
                        @Override
                        public void actionPerformed( ActionEvent e ){
                            try{
                                String script = workPath + File.separatorChar + s ;
                                script = "\"" + script + "\"" ;
                                Process prc = Runtime.getRuntime().exec( "cmd /C " + script ) ;
                                InputStream stdout = prc.getInputStream() ;
                                InputStream stderr = prc.getErrorStream() ;
                                OutputReader osI = new OutputReader( stdout ) ;
                                OutputReader osE = new OutputReader( stderr ) ;
                                osI.start() ;
                                osE.start() ;
                                //int exitValue = prc.waitFor() ;
                                //frame.setVisible( true ) ;
                                /*if( exitValue != 0 ){
                                    throw new IOException() ;
                                }*/
                            }catch( IOException ioe ){
                                System.out.println( "Error al ejecutar " + s ) ;
                                ioe.printStackTrace() ;
                            }catch( Exception ex ){
                                System.out.println( "Exception " + s ) ;
                            }finally{
                                System.exit( 0 ) ;
                            }
                        }
                    }
            ) ;
            frame.add( aux ) ;
            posY += 30 ;
        }
        JButton aux = new JButton( "Cancelar" ) ;
        aux.setBounds( 0, posY, 300, 30 ) ;
        aux.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed( ActionEvent e ){
                        System.exit( 0 ) ;
                    }
                }
        ) ;
        frame.add( aux ) ;
        posY += 30 ;

        frame.setSize( 300 , posY ) ;
        frame.setLayout( null ) ;
        frame.setLocation( (int)_mousePoint.getX(), (int)_mousePoint.getY() ) ;
        frame.setUndecorated( true ) ;
        frame.setVisible( true ) ;
    }

}

class OutputReader extends Thread{
    private static BufferedReader br ;
    public OutputReader( InputStream is ){
        this.br = new BufferedReader( new InputStreamReader( is ) ) ;
    }

    public void run(){
        try{
            String line;
            while( ( line = br.readLine() ) != null ){
            }
        }catch( IOException ioe ){
        }finally{
            try{
                br.close() ;
            }catch( Exception e){}
        }
    }
}
