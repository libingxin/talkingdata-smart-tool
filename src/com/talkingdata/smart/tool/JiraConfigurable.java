/*
 * The Talking Data ORM Tool Open Foundation licenses
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied, as more fully set forth in the License.
 *
 * See the NOTICE file distributed with this work for information regarding copyright ownership.
 *
 * @ author: bingxin.li@tendcloud.com
 */
package com.talkingdata.smart.tool;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import com.sun.istack.internal.NotNull;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class JiraConfigurable implements SearchableConfigurable {
    private SmartToolDialog contentPanel;

    @SuppressWarnings("FieldCanBeLocal")
    private final Project project;

    public JiraConfigurable(@NotNull Project project) {
        this.project = project;
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "TalkingData Smart Tool";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return "com.talkingdata.smart.tool.help";
    }

    @NotNull
    @Override
    public String getId() {
        return "com.talkingdata.smart.tool";
    }

    @Nullable
    @Override
    public Runnable enableSearch(String s) {
        return null;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        contentPanel = new SmartToolDialog();
        return contentPanel.getContentPane();
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void apply() throws ConfigurationException {
    }

    @Override
    public void reset() {
    }

    @Override
    public void disposeUIResources() {
        contentPanel = null;
    }
}
