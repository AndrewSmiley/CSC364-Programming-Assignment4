package csc364pa4;

/**
 * Created by Andrew on 7/11/16.
 */
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.TreeSet;

public class BSTAnimation extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        BST<Integer> tree = new BST<>(); // Create a tree

        BorderPane pane = new BorderPane();
        BTView view = new BTView(tree); // Create a View
        pane.setCenter(view);

        TextField tfKey = new TextField();
        tfKey.setPrefColumnCount(3);
        tfKey.setAlignment(Pos.BASELINE_RIGHT);
//        Search, Inorder, Preorder, Postorder, Breadth-First, and Height
        Button btInsert = new Button("Insert");
        Button btDelete = new Button("Delete");
        Button btSearch = new Button("Search");
        Button btInOrder = new Button("Inorder");
        Button btPreorder= new Button("Preorder");
        Button btPostorder= new Button("Postorder");
        Button btBreadthFirst = new Button("Breadth-First");
        Button btHeight = new Button("Height");
        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(new Label("Enter a key: "),
                tfKey, btInsert, btDelete, btSearch,btInOrder,btPreorder,btPostorder,btBreadthFirst,btHeight);
        hBox.setAlignment(Pos.CENTER);
        pane.setBottom(hBox);

        btInsert.setOnAction(e -> {
            int key = Integer.parseInt(tfKey.getText());
            if (tree.search(key)) { // key is in the tree already
                view.displayTree();
                view.setStatus(key + " is already in the tree");
            }
            else {
                tree.insert(key); // Insert a new key
                view.displayTree();
                view.setStatus(key + " is inserted in the tree");
            }
        });

        btDelete.setOnAction(e -> {
            int key = Integer.parseInt(tfKey.getText());
            if (!tree.search(key)) { // key is not in the tree1
                view.displayTree();
                view.setStatus(key + " is not in the tree");
            }
            else {
                tree.delete(key); // Delete a key
                view.displayTree();
                view.setStatus(key + " is deleted from the tree");
            }
        });
        btSearch.setOnAction(e-> {
                    int key = Integer.parseInt(tfKey.getText());
                    TreeSet<Integer> trace = tree.searchTrace(key);
                    if (!trace.contains(key)) {
                        view.displayTreeColors(trace);
                        view.setStatus(key + " is not in the tree");
                    }
                    if (trace.contains(key)) {
                        view.displayTreeColors(trace);
                        view.setStatus(key + " found in tree");
                    }
                });
        btInOrder.setOnAction(e->{
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            for (Integer i : tree.inorder(new ArrayList<Integer>())){
                stringBuilder.append(i.toString()+",");

            }
            stringBuilder.replace(stringBuilder.length()-1, stringBuilder.length(), "");
            stringBuilder.append("]");
            view.displayTree();
            view.setStatus("Inorder Traversal: "+stringBuilder.toString());
        });
        btPreorder.setOnAction(e->{
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            for (Integer i : tree.preorder(new ArrayList<Integer>())){
                stringBuilder.append(i.toString()+",");

            }
            stringBuilder.replace(stringBuilder.length()-1, stringBuilder.length(), "");
            stringBuilder.append("]");
            view.displayTree();
            view.setStatus("Pre-order Traversal: "+stringBuilder.toString());
        });
        btPostorder.setOnAction(e->{
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            for (Integer i : tree.postorder(new ArrayList<Integer>())){
                stringBuilder.append(i.toString()+",");

            }
            stringBuilder.replace(stringBuilder.length()-1, stringBuilder.length(), "");
            stringBuilder.append("]");
            view.displayTree();
            view.setStatus("Post-order Traversal: "+stringBuilder.toString());

        });
        btBreadthFirst.setOnAction(e->{
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            for (Integer i : tree.breadthFirst(new ArrayList<Integer>())){
                stringBuilder.append(i.toString()+",");

            }
            stringBuilder.replace(stringBuilder.length()-1, stringBuilder.length(), "");
            stringBuilder.append("]");
            view.displayTree();
            view.setStatus("Breadth-First Traversal: "+stringBuilder.toString());

        });
        btHeight.setOnAction(e->{
            view.displayTree();
            view.setStatus("Height: "+ tree.height());
        });
//            public boolean search(E element) {
//                TreeNode<E> current = root; // Start from the root
//                while (current != null)
//                    if (element < current.element) {
//                        current = current.left; // Go left
//                    }
//                    else if (element > current.element) {
//                        current = current.right; // Go right
//                    }
//                    else // Element matches current.element
//                        return true; // Element is found
//                return false; // Element is not in the tree
//            }



        // Create a scene and place the pane in the stage
        Scene scene = new Scene(pane, 450, 250);
        primaryStage.setTitle("BSTAnimation"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}