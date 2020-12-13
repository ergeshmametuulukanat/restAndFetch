function openModalWindow(id) {
    document.getElementById("idEdit").value = id;
    document.getElementById("firstNameEdit").value = $("#name" + id).text();
    document.getElementById("lastNameEdit").value = $("#lastName" + id).text();
    document.getElementById("ageEdit").value = $("#age" + id).text();
    document.getElementById("emailEdit").value = $("#email" + id).text();
    document.getElementById("passwordEdit").value = $("#password" + id).text();
}

function openModalWindowDel(id) {
    document.getElementById("idDelete").value = id;
    document.getElementById("firstNameDelete").value = $("#name" + id).text();
    document.getElementById("lastNameDelete").value = $("#lastName" + id).text();
    document.getElementById("ageDelete").value = $("#age" + id).text();
    document.getElementById("emailDelete").value = $("#email" + id).text();
    document.getElementById("passwordDelete").value = "";
}