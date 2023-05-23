function onSubmitForm() {
    let steamID = document.getElementById("steamIdInput").value;
    document.steamUserForm.action="stats/"+steamID;
}