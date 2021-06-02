# Fuse on OpenShift - Practical Labs

## Lab0: Set-up client environment

### Summary
This lab is intended to install the developers client environment. This includes

- OpenShift *oc* CLI-client
- Camel K *kamel* CLI-client
- Knative *kn* CLI-client
- VisualStudio code IDE with Extensions:
 - Extension Pack for Apache Camel by Red Hat

### Step 1: Create an openshift directory and add it to your $PATH variable
This steps is dependand on your operating system. Choose accordingly:

***Windows***

Create a folder called openshift in your Home Directory. Add it to your $PATH variable using the system control panel.

***Linux/MacOS***

Create a folder called openshift in your $HOME directoy. Add it to your $PATH variable.

```
$ mkdir ~/openshift
$ export PATH=~/openshift:$PATH
```

> It may be helpful to add the adjustment of your $PATH variable to your local .bashrc

### Step 2: *oc* client
Open the OpenShift Console and navigate to the *?* Menu in the upper right corner. Click on *Command line tools*

Download the right archive for your platform and operating system. Move it to the folder created in step 1.

### Step 3: *kamel* client
Open the OpenShift Console and navigate to the *?* Menu in the upper right corner. Click on *Command line tools*

Download the right archive for your platform and operating system. Move it to the folder created in step 1.

### Step 4: *kn* client
Open the OpenShift Console and navigate to the *?* Menu in the upper right corner. Click on *Command line tools*

Download the right archive for your platform and operating system. Move it to the folder created in step 1.

### Step 5: Install Visual Studio Code
Download and Install Visual Studio Code according to the [installation documentation](https://code.visualstudio.com/docs/setup/setup-overview).

### Step 6: Add *Extension Pack for Apache Camel by Red Hat* to Visual Studio Code
Start Visual Studio Code and open the command palette by pressing ```Ctrl+Shift+P```.

In the text field type *>Extensions: Install Extensions* and confirm by pressing  ```Enter```

In the extensions menu search for *Extension Pack for Apache Camel by Red Hat*. Select it and click on *Install*
