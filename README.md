 # Image Viewer (Graphical Interface)

<br><br>
### Software Usage
***

The software's operation is simple. Initially, the graphical interface consists of three buttons. The main button when starting the application is the folder selector button; 
clicking on it allows you to choose a folder. If the selected folder contains images in compatible formats, they will be displayed in the image viewer. If no compatible images are found in the folder,
it will display a default image indicating that there are no compatible images in that folder. 
Subsequently, you can use the two remaining buttons to switch between images, or you can also change them by dragging them with the mouse.
Functionality has also been added to the right-click, allowing you to view image specifications such as dimensions or size.

An image of the graphical interface is attached to facilitate the explanation:

![image](https://github.com/DevGiovanniLC/image-viewer/assets/92268681/5eea5dca-84a7-4df3-b177-08c1989265de)

<br><br>
### Architecture
***
The software is built in Java, making use of the JavaFX framework oriented towards building applications with a graphical interface using the Model-View-Controller (MVC) architecture design.
The Model-View-Presenter (MVP) architecture is also applied when changing images by dragging them with the mouse. Additionally, the application utilizes JavaFX's own fxml markup language to create the graphical interface.

<br><br>
### Dependencies and APIs
***
The application makes use of the JavaFX framework and dependencies employed by the framework.

<br><br>
