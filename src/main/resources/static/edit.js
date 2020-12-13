document.getElementById("editModal").addEventListener("submit", editPost)

function editPost(e){
    e.preventDefault();

    let id = document.getElementById("idEdit").value;
    let firstName = document.getElementById("firstNameEdit").value;
    let lastName = document.getElementById("lastNameEdit").value;
    let age = document.getElementById("ageEdit").value;
    let email = document.getElementById("emailEdit").value;
    let password = document.getElementById("passwordEdit").value;
    let roles = setRoles(Array.from(document.getElementById("roleEdit").selectedOptions)
        .map(option => option.value));

    fetch("http://localhost:8088/editUser", {
        method:"PUT",
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
            roles:roles
        })
    }).finally(() => {
        $('#edit').modal("hide")
        getUsers();
    })
}