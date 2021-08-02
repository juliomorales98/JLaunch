import java.awt.event.* ;

class myMouseListener implements MouseListener{
    @Override
    public void mouseClicked( MouseEvent arg0 ){
        System.out.println( "Click" ) ;
    }
    @Override
    public void mousePressed( MouseEvent e ){}
    @Override
    public void mouseReleased( MouseEvent e ){}
    @Override
    public void mouseEntered( MouseEvent e ){}
    @Override
    public void mouseExited( MouseEvent e ){}
}
