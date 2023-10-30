# Notes App

Notes app that allows for adding, editing and deleting of notes and their descriptions.

## Functionality 

The following **required** functionality is completed:

* [ ] User sees UI for adding, removing, and editing notes.
* [ ] User can add notes.
* [ ] User can edit notes.
* [ ] User can remove notes.
* [ ] Notes are stored in a Firebase realtime database.
* [ ] Users can login and see their own notes only through Firebase authentication.
* [ ] Has dialog confirmation for removing notes.
* [ ] Prompts user to sign up.
* [ ] Contains splash screen.

The following **extensions** are implemented:

* implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
* implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
* Google Firebase
* Safeargs

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://github.com/chanothy/NotesApp/blob/master/notesApp.AuthDBDemo.gif' title='Video Walkthrough' width='50%' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

I had a lot of issues trying to get the add and edit functionality to work. They would work independently, but once I chained them together, I got a fragment navigation error. It was also difficult trying to get the toolbars to work because they were hard to understand. I spent a lot of time connecting the Firebase database.

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
