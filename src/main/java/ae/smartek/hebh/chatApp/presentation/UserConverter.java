/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ae.smartek.hebh.chatApp.presentation;

import ae.smartek.hebh.chatApp.model.User;
import java.util.LinkedHashMap;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author houssem
 */
@FacesConverter(value = "userConverter")
public class UserConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if ((value == null) || (value.equals(""))) {
            return null;
        }
        ValueExpression vex
                = context.getApplication().getExpressionFactory()
                        .createValueExpression(context.getELContext(),
                                "#{chatAppCtrl}", ChatAppCtrl.class);

        ChatAppCtrl chatAppCtrl = (ChatAppCtrl) vex.getValue(context.getELContext());
       return chatAppCtrl.getUserByLabel(value);
       
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof String) {
            return null;
        }
        User u=(User)value;
        return u.getFirstName()+" "+u.getLastName();
    }

}
