function myFunction() {
    let value = "false";
    let text = "Are you sure to delete?"
    if (confirm(text) == true) {
        value = "true";
    } else {
        value = "false";
    }
    document.getElementById("deleteItem").value=value;
}