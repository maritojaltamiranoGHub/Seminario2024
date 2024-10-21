package main.java.sigep.views;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.List;

/**
 *
 * @author Marito
 */
public class FocusPolicy extends FocusTraversalPolicy {

    private List<Component> components;

    public FocusPolicy(List<Component> components) {
        this.components = components;
    }

    @Override
    public Component getComponentAfter(Container aContainer, Component aComponent) {
        int index = (components.indexOf(aComponent) + 1) % components.size();
        Component after = components.get(index);
        while (index < components.size() && !(after.isEnabled() && after.isVisible())) {
            index++;
            after = components.get(index);
        }
        return after;
    }

    @Override
    public Component getComponentBefore(Container aContainer, Component aComponent) {
        int index = (components.indexOf(aComponent) - 1);
        if (index < 0) {
            index = components.size() - 1;
        }
        Component before = components.get(index);
        while (index >= 0 && !(before.isEnabled() && before.isVisible())) {
            index--;
            before = components.get(index);
        }
        return before;
    }

    @Override
    public Component getFirstComponent(Container aContainer) {
        int index = 0;
        Component first = components.get(index);
        while (index < components.size() && !(first.isEnabled() && first.isVisible())) {
            index++;
            first = components.get(index);
        }
        return first;
    }

    @Override
    public Component getLastComponent(Container aContainer) {
        int index = components.size() - 1;
        Component last = components.get(index);
        while (index >= 0 && !(last.isEnabled() && last.isVisible())) {
            index--;
            last = components.get(index);
        }
        return last;
    }

    @Override
    public Component getDefaultComponent(Container aContainer) {
        return getFirstComponent(aContainer);
    }
}
