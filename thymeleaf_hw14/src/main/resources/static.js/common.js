function attachUser(sculptorId, sculptureId) {
    console.log('sculptureId', sculptureId)
    console.log('sculptorId', sculptorId)
    submitRequest(sculptorId, sculptureId, true)
}

function detachUser(sculptorId, sculptureId) {
    console.log('sculptureId', sculptureId)
    console.log('sculptorId', sculptorId)
    submitRequest(sculptorId, sculptureId, false)
}

function submitRequest(sculptorId, sculptureId, attachOrDetach) {
    let depAttachSubmit = document.getElementById('depAttachSubmit');
    if (depAttachSubmit) {
        let depAttach = document.getElementById('depAttach');
        let input = document.createElement("input");
        input.setAttribute("type", "hidden");
        input.setAttribute("name", "sculptorId");
        input.setAttribute("value", sculptorId);
        depAttach.appendChild(input);
        input = document.createElement("input");
        input.setAttribute("type", "hidden");
        input.setAttribute("name", "sculptureId");
        input.setAttribute("value", sculptureId);
        depAttach.appendChild(input);
        input = document.createElement("input");
        input.setAttribute("type", "hidden");
        input.setAttribute("name", "attachOrDetach");
        input.setAttribute("value", attachOrDetach);
        depAttach.appendChild(input);
        depAttachSubmit.click();
    }
}

