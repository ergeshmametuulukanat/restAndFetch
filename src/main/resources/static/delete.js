document.getElementById("deleteModal").addEventListener("submit", deletePost)

function deletePost(e){
    e.preventDefault();

    let id = document.getElementById("idDelete").value;
    let firstName = document.getElementById("firstNameDelete").value;
    let lastName = document.getElementById("lastNameDelete").value;
    let age = document.getElementById("ageDelete").value;
    let email = document.getElementById("emailDelete").value;
    let password = document.getElementById("passwordDelete").value;
    let roles = setRoles(Array.from(document.getElementById("roleDelete").selectedOptions)
        .map(option => option.value));

    fetch("http://localhost:8088/deleteUser", {
        method:"DELETE",
        headers: {
            "Accept": "application/json, text/plain, */*",
            "Content-type":"application/json"
        },
        body:JSON.stringify({
            id:id,
            name:firstName,
            lastName:lastName,
            age:age,
            email:email,
            password:password,
            roles:roles})
    }).finally(() => {
        $('#delete').modal("hide")
        getUsers();
    })
}