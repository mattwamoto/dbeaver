/*
 * DBeaver - Universal Database Manager
 * Copyright (C) 2010-2023 DBeaver Corp and others
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jkiss.dbeaver.ui.navigator.actions;

import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.jkiss.dbeaver.Log;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.runtime.DBWorkbench;

public class NavigatorActiveProjectContributor extends ContributionItem
{
    private static final Log log = Log.getLog(NavigatorActiveProjectContributor.class);

    @Override
    public void fill(Menu menu, int index)
    {
        createMenu(menu);
    }
    
    private void createMenu(final Menu menu)
    {
        final DBPProject activeProject = DBWorkbench.getPlatform().getWorkspace().getActiveProject();
        for (final DBPProject project : DBWorkbench.getPlatform().getWorkspace().getProjects()) {
            MenuItem txnItem = new MenuItem(menu, SWT.RADIO);
            txnItem.setText(project.getName());
            txnItem.setSelection(project == activeProject);
            txnItem.setData(project);
            txnItem.addSelectionListener(new SelectionAdapter()
            {
                @Override
                public void widgetSelected(SelectionEvent e)
                {
                    NavigatorHandlerProjectSetActive.setActiveProject(project);
                }
            });
        }
    }

}