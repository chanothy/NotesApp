# Notes App

Notes app that allows for adding, editing and deleting of notes and their descriptions.

## Functionality 

The following **required** functionality is completed:

* [ ] User sees UI for adding, removing, editing notes.
* [ ] User can add notes.
* [ ] User can edit notes.
* [ ] User can remove notes.
* [ ] Notes are stored in a database.

The following **extensions** are implemented:

* implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
* implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
* implementation ("androidx.room:room-runtime:$room_version")
* implementation ("androidx.room:room-ktx:$room_version")
* ksp("androidx.room:room-compiler:2.4.1")

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://github.com/chanothy/NotesApp/blob/master/notesAppDemo.gif' title='Video Walkthrough' width='50%' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

There was an issue with the delete button. It was related to how the database getting worked. The value is not immediately available so you need to delay any actions on it until it is available. This happend with delete because, getting a value from the dao was slow, so I needed to await() until it was available for deletion.

## License

    Copyright [2023] [Timothy Chan]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
