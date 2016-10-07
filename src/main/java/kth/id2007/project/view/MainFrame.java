package kth.id2007.project.view;

import kth.id2007.project.model.Roles;
import kth.id2007.project.model.User;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Main frame of the system
 * <p>
 * Created by kim on 2016-10-06.
 */
public class MainFrame extends JFrame {

    private GUI gui;
    private User user;

    /**
     * Class constructor. Initializes the frame
     *
     * @param gui  controller
     * @param user logged in user
     */
    public MainFrame(GUI gui, User user) {
        setName("main_frame");
        this.gui = gui;
        this.user = user;
        setLayout(new MigLayout());
        setTitle("SEP System");
        Container container = new Container();
        container.setName("container_pane");
        setContentPane(container);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); //center on screen
        setVisible(true);
    }

    //Container that holds tabs
    private class Container extends JTabbedPane {

        public Container() {
            HomePanel homePanel = new HomePanel();
            if (homePanel.access())
                addTab("Home", homePanel);
            EventApplicationsPanel eventApplicationsPanel = new EventApplicationsPanel();
            if (eventApplicationsPanel.access())
                addTab("Event Applications", eventApplicationsPanel);
            ClientsPanel clientsPanel = new ClientsPanel();
            if (clientsPanel.access())
                addTab("Clients", clientsPanel);
            EmployeesPanel employeesPanel = new EmployeesPanel();
            if (employeesPanel.access())
                addTab("Employees", employeesPanel);
        }
    }

    //Home-panel/tab
    private class HomePanel extends JPanel {
        String[] accessList = Roles.roles();

        private HomePanel() {
            setLayout(new MigLayout("wrap 2"));
            JLabel lbl = new JLabel("Home");
            lbl.setName("home_title");
            add(lbl, "span 2, center");
            lbl = new JLabel("User:");
            lbl.setName("user_label");
            add(lbl, "span 1");
            lbl = new JLabel(user.getUsername());
            lbl.setName("username");
            add(lbl, "span 1");
            lbl = new JLabel("Role:");
            lbl.setName("role_label");
            add(lbl, "span 1");
            lbl = new JLabel(user.getRole());
            lbl.setName("role");
            add(lbl, "span 1");
            lbl = new JLabel("Team:");
            lbl.setName("team_label");
            add(lbl, "span 1");
            lbl = new JLabel(user.getTeam());
            lbl.setName("team");
            add(lbl, "span 1");
            CreateHrRequestPanel createHrRequestPanel = new CreateHrRequestPanel();
            CreateBudgetIssueRequestPanel createBudgetIssueRequestPanel = new CreateBudgetIssueRequestPanel();
            if (createHrRequestPanel.access())
                add(createHrRequestPanel, "span 2, center, gaptop 30");
            if(createBudgetIssueRequestPanel.access())
                add(createBudgetIssueRequestPanel, "span 2, center");
        }

        private class CreateHrRequestPanel extends JPanel {
            private String[] accessList = new String[]{
                    Roles.PRODUCTION_MANAGER, Roles.SERVICE_MANAGER, Roles.ADMINISTRATOR
            };

            private CreateHrRequestPanel() {
                setLayout(new MigLayout("wrap 1"));
                add(new JLabel("Create Human Resources Request"), "span 1");
            }

            private boolean access() {
                return Arrays.stream(accessList).anyMatch(accessRole -> accessRole.equals(user.getRole()) | accessRole.equals(user.getTeam()));
            }
        }

        private class CreateBudgetIssueRequestPanel extends JPanel {
            private String[] accessList = new String[]{
                    Roles.PRODUCTION_MANAGER, Roles.SERVICE_MANAGER, Roles.ADMINISTRATOR
            };

            private CreateBudgetIssueRequestPanel() {
                setLayout(new MigLayout("wrap 1"));
                add(new JLabel("Create Budget Issue Request"), "span 1");
            }

            private boolean access() {
                return Arrays.stream(accessList).anyMatch(accessRole -> accessRole.equals(user.getRole()) | accessRole.equals(user.getTeam()));
            }
        }

        private boolean access() {
            return Arrays.stream(accessList).anyMatch(accessRole -> accessRole.equals(user.getRole()) | accessRole.equals(user.getTeam()));
        }

    }

    //EventApplications-panel/tab
    private class EventApplicationsPanel extends JPanel {

        private String[] accessList = new String[]{
                Roles.SENIOR_CUSTOMER_SERVICE_OFFICER,
                Roles.FINANCIAL_MANAGER, Roles.ADMINISTRATION_MANAGER, Roles.PRODUCTION_MANAGER, Roles.SERVICE_MANAGER,
                Roles.PHOTOGRAPHER_TEAM, Roles.AUDIO_TEAM, Roles.GRAPHIC_TEAM, Roles.IT_TEAM, Roles.DECORATING_TEAM,
                Roles.COOKING_TEAM, Roles.RESTAURANT_SERVICES_TEAM, Roles.ADMINISTRATOR
        };

        private EventApplicationsPanel() {
            setLayout(new MigLayout("wrap 1"));
            CreateEventApplicationPanel createEventApplicationPanel = new CreateEventApplicationPanel();
            if (createEventApplicationPanel.access())
                add(createEventApplicationPanel, "span 1");
            add(new ViewEventApplicationPanel());
        }

        private boolean access() {
            return Arrays.stream(accessList).anyMatch(accessRole -> accessRole.equals(user.getRole()) | accessRole.equals(user.getTeam()));
        }

        private class CreateEventApplicationPanel extends JPanel {
            private String[] accessList = new String[]{
                    Roles.SENIOR_CUSTOMER_SERVICE_OFFICER, Roles.ADMINISTRATOR
            };

            public CreateEventApplicationPanel() {
                setLayout(new MigLayout("wrap 2"));
                add(new JLabel("Create Event Application"), "span 2, center");
            }

            private boolean access() {
                return Arrays.stream(accessList).anyMatch(accessRole -> accessRole.equals(user.getRole()) | accessRole.equals(user.getTeam()));
            }
        }

        private class ViewEventApplicationPanel extends JPanel {
            public ViewEventApplicationPanel() {
                setLayout(new MigLayout("wrap 2"));
                add(new JLabel("Event Applications"), "span 2, center");
            }
        }

    }

    //Clients-panel/tab
    private class ClientsPanel extends JPanel {
        private String[] accessList = new String[]{
                Roles.SENIOR_CUSTOMER_SERVICE_OFFICER, Roles.FINANCIAL_MANAGER,
                Roles.MARKETING_OFFICER, Roles.MARKETING_ASSISTANT, Roles.ADMINISTRATOR
        };

        private ClientsPanel() {
            setLayout(new MigLayout("wrap 1"));
            add(new JLabel("Clients"), "span 1, center");
        }

        private boolean access() {
            return Arrays.stream(accessList).anyMatch(accessRole -> accessRole.equals(user.getRole()) | accessRole.equals(user.getTeam()));
        }
    }

    //Employees-panel/tab
    private class EmployeesPanel extends JPanel {
        private String[] accessList = new String[]{
                Roles.FINANCIAL_MANAGER, Roles.SENIOR_HR_MANAGER, Roles.HR_ASSISTANT,
                Roles.ADMINISTRATION_MANAGER, Roles.ADMINISTRATOR
        };

        private EmployeesPanel() {
            setLayout(new MigLayout("wrap 1"));
            add(new JLabel("Employees"), "span 1, center");
            String[] columnNames = new String[]{"username", "role", "team"};
            String rowData[][] = createTableModel();
            DefaultTableModel model = new DefaultTableModel(rowData, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            JTable table = new JTable(model);
            table.setPreferredScrollableViewportSize(table.getPreferredSize());
            table.setFillsViewportHeight(true);
            JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            Dimension dim = new Dimension(800, 400);
            int rowsDisplayed = 15;
            scrollPane.setPreferredSize(new Dimension(dim.width, table.getRowHeight() * rowsDisplayed));
            add(scrollPane, "span 1");
        }

        private String[][] createTableModel() {
            ArrayList<User> users = gui.getUsers();
            String[][] rowData = new String[users.size()][3];
            for (int i = 0; i < users.size(); i++) {
                User u = users.get(i);
                rowData[i][0] = u.getUsername();
                rowData[i][1] = u.getRole();
                rowData[i][2] = u.getTeam();
            }
            return rowData;
        }

        private boolean access() {
            return Arrays.stream(accessList).anyMatch(accessRole -> accessRole.equals(user.getRole()) | accessRole.equals(user.getTeam()));
        }
    }
}