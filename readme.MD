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
    [0, 100, 0], [100, 100, 0], [100, 0, 0], [0, 0, 0],
    [0, 100, 100], [100, 100, 100], [100, 0, 100], [0, 0, 100]
  ],
  "Edges": [
    [0, 1], [1, 2], [2, 3], [3, 0],
    [7, 6], [6, 5], [5, 4], [4, 7],
    [0, 4], [1, 5], [2, 6], [3, 7]
  ],
  "Faces": [ // -- Optional without color section.
    [0, 1, 2, 3], [4, 5, 6, 7],
    [0, 1, 5, 4], [3, 2, 6, 7],
    [1, 2, 6, 5], [0, 3, 7, 4]
  ],
  "Color": [ // -- Optional, but need Faces.
    [255, 80, 80], [255, 102, 102],
    [204, 0, 0], [255, 51, 0],
    [102, 0, 51], [153, 0, 0]
  ]
}
```
