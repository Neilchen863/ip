# HiChat User Guide

## Introduction
Welcome to **HiChat**, a Java-based chatbot application designed to provide a smart and engaging conversation experience. HiChat allows users to set reminders, interact conversationally, and manage tasks effectively.

## Adding Deadlines
To add a deadline in HiChat, use the `deadline` keyword followed by a task description and a due date.

**Example Usage:**
```
deadline Submit assignment /by 2025-02-25 23:59
```

**Expected Outcome:**
```
Got it. I've added this deadline:
  [D][ ] Submit assignment (by: Feb 25 2025, 23:59)
```

## Feature ABC
This feature allows users to mark tasks as completed. Use the `done` command followed by the task number.

**Example Usage:**
```
mark 2
```

**Expected Outcome:**
```
Nice! I've marked this task as done:
  [✓] Task Name
```

## Feature XYZ
This feature allows users to delete tasks. Use the `delete` command followed by the task number.

**Example Usage:**
```
delete 1
```

**Expected Outcome:**
```
Noted. I've removed this task:
  [ ] Task Name
```

