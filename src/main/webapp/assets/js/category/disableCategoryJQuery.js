$(".deactivate-category-button").click(function (){
    const button = this;
    const tr = button.closest("tr");
    const id = $(tr).data('catId');
    $.post("/changeCategoryStatus/" + id, function (){
        const active = tr.querySelector(".ativo");
        active.innerHTML = "Inativa";
        button.remove();
    });
});

$(".deactivate-subcategory-button").click(function (){
    const button = this;
    const tr = button.closest("tr");
    const id = $(tr).data('subId');
    $.post("/changeSubcategoryStatus/" + id, function (){
        const active = tr.querySelector(".ativo");
        active.innerHTML = "Inativa";
        button.remove();
    });
});
