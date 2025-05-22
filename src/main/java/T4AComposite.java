import java.awt.*;

/**
 * Composite used by decorations to implement the composite design pattern
 *
 * @version 1
 * @author  owen-mcmanus
 */
public abstract class T4AComposite implements T4AComponent {
    private T4AComponent component;

    @Override
    public void draw(Graphics g) {
        if(component != null){
            component.draw(g);
        }
    }

    public void addComponent(T4AComponent c){
        component = c;
    }
}
