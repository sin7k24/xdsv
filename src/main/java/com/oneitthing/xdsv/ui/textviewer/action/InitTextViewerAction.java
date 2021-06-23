package com.oneitthing.xdsv.ui.textviewer.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JTextArea;

import com.oneitthing.swingcontrollerizer.action.BaseAction;
import com.oneitthing.swingcontrollerizer.controller.ParameterMapping;
import com.oneitthing.xdsv.ui.textviewer.TextViewerPanel;

public class InitTextViewerAction  extends BaseAction {

    @Override
    protected boolean prepare(ParameterMapping parameterMapping) throws Exception {
        try {
            TextViewerPanel textViewerPanel = (TextViewerPanel)parameterMapping.getEventSource();
            File file = textViewerPanel.getFile();
            
            JTextArea textArea = (JTextArea)getComponent(textViewerPanel, "textViewerPanel.textArea");
            
            BufferedReader reader = new BufferedReader(new FileReader(new File(file.getAbsolutePath() + File.separator + "index.rdf")));
            String line;
            while((line = reader.readLine()) != null) {
                textArea.append(line + System.getProperty("line.separator"));
            }
            
            textArea.setSelectionStart(0);
            textArea.setSelectionEnd(0);
        } catch (FileNotFoundException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }

        
        return false;
    }


}
