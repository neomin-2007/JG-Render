# JG-Render

This project is a renderer that converts three-dimensional (3D) objects represented in .json files into a two-dimensional (2D) projection. The renderer uses Java and the javax.swing library to draw the edges of the 3D object on a 2D canvas. It also includes interactive features such as rotation, translation, and zooming.

## Features

- **JSON File Parsing**: The renderer accepts `.json` files containing the definition of vertices and edges of a 3D object.
- **3D to 2D Projection**: Uses a simple projection to convert 3D coordinates into 2D.
- **Rotation and Movement**: The renderer allows you to rotate and move the object.
- **Real-Time Rendering**: Draws the edges of the 3D object in a graphical window.

# Project Structure

This project consists of two main classes:

1. **`RenderPanel`**: Responsible for rendering the 3D object onto a 2D screen.
    - **Method `project`**: Converts 3D coordinates into 2D.
    - **Method `paintComponent`**: Draws the edges of the object on the screen.

2. **`Geometry`**: Represents the geometry of the 3D object.
    - **Attributes**:
        - `vertex`: A matrix of vertices (3D coordinates).
        - `edges`: A matrix of edges (connections between vertices).

## Archive Example

The `.json` archive example:

```json
{
  "Vertex": [
    [-100, 50, 100], [100, 50, 100], [150, 100, 50], [100, 150, 100],
    [-100, 150, 100], [-150, 100, 50], [-100, 200, 100], [100, 200, 100],
    [150, 225, 50], [100, 300, 100], [-100, 300, 100], [-150, 225, 50]
  ],
  "Edges": [
    [0, 1], [1, 2], [2, 3], [3, 4], [4, 5],
    [5, 0], [6, 7], [7, 8], [8, 9], [9, 10],
    [10, 11], [11, 6], [0, 6], [1, 7], [2, 8],
    [3, 9], [4, 10], [5, 11]
  ]
}
