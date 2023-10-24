const listSelects = document.querySelectorAll(".selects-status");

for(let tmp of listSelects)
    tmp.addEventListener("change", (e)=>{
        e.currentTarget.parentElement.submit();
    });