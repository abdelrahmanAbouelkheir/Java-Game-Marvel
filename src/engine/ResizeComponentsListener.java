package engine;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import javax.swing.JPanel;

/**
 *
 * @author Bilal Gharib
 */
public class ResizeComponentsListener implements HierarchyBoundsListener {
    
    private int InitialPaneWidth;
    private int InitialPaneHeight;   
    private double InitialArea;
    private final boolean ResizeFont;

    public ResizeComponentsListener(
        int InitialPaneWidth, int InitialPaneHeight, boolean ResizeFont) {
        
        this.InitialPaneWidth = InitialPaneWidth;
        this.InitialPaneHeight = InitialPaneHeight;    
        this.InitialArea = InitialPaneWidth * InitialPaneHeight;
        this.ResizeFont = ResizeFont;
    }
            
    @Override
    public void ancestorMoved(HierarchyEvent e) {
        // not used        
    }

    @Override
    public void ancestorResized(HierarchyEvent e) {
                
        JPanel Panel = (JPanel)e.getSource();
        
        double RatioX = (double)Panel.getWidth() / InitialPaneWidth;
        double RatioY = (double)Panel.getHeight() / InitialPaneHeight;
        
        double CurrentArea = Panel.getWidth() * Panel.getHeight();
        double RatioArea = (double)CurrentArea / InitialArea;
        
        double NewX, NewY, NewWidth, NewHeight, NewFontSize;
        Font NewFont;
                
        for(Component c : Panel.getComponents()){            
            NewX = c.getX() * RatioX;
            NewY = c.getY() * RatioY;
            NewWidth = c.getWidth() * RatioX;
            NewHeight = c.getHeight() * RatioY;               
            c.setLocation((int)Math.round(NewX), (int)Math.round(NewY));
            c.setSize((int)Math.round(NewWidth), (int)Math.round(NewHeight));            
            if (ResizeFont){
                NewFontSize = c.getFont().getSize() * RatioArea;
                NewFont = c.getFont().deriveFont((float)Math.round(NewFontSize));
                c.setFont(NewFont);                
            }
            
            
            
        }                
        this.InitialPaneWidth = Panel.getWidth();
        this.InitialPaneHeight = Panel.getHeight();
        this.InitialArea = CurrentArea;   
    }    
}
