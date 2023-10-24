<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="WEB-INF/assets/header.jsp"%>
<script>
    if(${error.isEmpty() == false})
        Swal.fire({
            title: "${error}",
            icon: "error",
            showCancelButton: true
        })
</script>
<a href="${pageContext.servletContext.contextPath}/client" class="p-4 bg-[#015CC8] rounded-md text-white absolute top-30 right-10">Ajouter client</a>
<div class="bg-[#EEF1F6] flex justify-center items-center gap-8 w-screen h-screen">
<div class="w-[400px] bg-white p-6 flex flex-col gap-5 form1">
    <p class="font-bold">Crédit Simulation</p>
    <div class="flex flex-col gap-4">
        <label for="amount" class="font-bold text-[12px]">Valeur</label>
        <input type="number" min="1000" max="10000" name="amount" id="amount" class="border border-[2px] border-gray-300 rounded-[4px] p-1 w-full ">
    </div>
    <div class="flex flex-col gap-3">
        <label for="months" class="font-bold flex w-full justify-between items-center text-[12px]">
            <span>Mois</span>
            <span class="text-[13px] mt-3 months">4</span>
        </label>
        <input type="range" min="4" max="24" name="months" id="months" class="range-sm range-input">
        <div class="flex w-full justify-between items-center text-gray-400 font-light text-[12px]">
            <span>4</span>
            <span>24</span>
        </div>
    </div>
</div>
<div class="w-[400px] bg-white p-6 flex flex-col gap-3 form2">
    <p class="font-bold">Résultat</p>
    <p class="w-full bg-gray-300 text-right font-bold px-3 py-1">
        total
    </p>
    <div class="w-full flex justify-between items-center  py-2 border-b border-1 border-gray-300 border-dashed text-[12px] font-medium">
        <span>Valeur Crédit</span>
        <span class="amount">0 dh</span>
    </div>
    <div class="w-full flex justify-between items-center  py-2 border-b border-1 border-gray-300 border-dashed text-[12px] font-medium">
        <span>Taux d'interet</span>
        <span >12%</span>
    </div>
    <div class="w-full flex justify-between items-center  py-2 border-b border-1 border-gray-300 border-dashed text-[12px] font-medium">
        <span>Mois</span>
        <span class="months">24</span>
    </div>
    <div class="w-full flex justify-between items-center  py-2 border-t border-1 border-gray-300 mt-7">
        <span>total</span>
        <span class="total">0 dh</span>
    </div>
    <button class="w-full bg-[#01B062] text-[15px] rounded-md p-2 text-white backTo next">suivant</button>
</div>
<div class="w-[500px] rounded-sm p-4 bg-white form3 hidden">
    <form action="${pageContext.servletContext.contextPath}" method="POST" class="w-full flex flex-col justify-center items-start gap-3">
        <p class="text-center w-full font-bold">Formulaire d'ajout</p>
        <div class="flex flex-col justify-center items-start w-full">
            <label for="duration" class="font-semibold">Mensualité</label>
            <input required type="number" name="duration" id="duration" class="p-2 w-full order border-[2px] border-gray-300 rounded-[4px] mt-1">
        </div>
        <div class="flex flex-col justify-center items-start w-full">
            <label for="value" class="font-semibold">Valeur</label>
            <input required type="number" name="amount" id="value" class="p-2 w-full order border-[2px] border-gray-300 rounded-[4px] mt-1">
        </div>
        <div class="flex flex-col justify-center items-start w-full">
            <label for="remark" class="font-semibold">Remarque</label>
            <textarea required name="remark" id="remark" class="p-2 w-full order border-[2px] border-gray-300 rounded-[4px] mt-1"></textarea>
        </div>
        <input hidden value="12" name="tax">
        <div class="flex flex-col justify-center items-start w-full">
            <label for="client" class="font-semibold">Client</label>
            <select required id="client" name="client" class="p-2 w-full order border-[2px] border-gray-300 rounded-[4px] mt-1">
                <c:forEach items="${clients}" var="client">
                    <option value="${client.code}">${client.firstName} ${client.lastName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="flex flex-col justify-center items-start w-full">
            <label for="employee" class="font-semibold">Employee</label>
            <select required id="employee" name="employee" class=" p-2 w-full order border-[2px] border-gray-300 rounded-[4px] mt-1">
                <c:forEach items="${emploies}" var="employee">
                    <option value="${employee.registrationNbr}">${employee.firstName} ${employee.lastName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="flex flex-col justify-center items-start w-full">
            <label for="agency" class="font-semibold">Agence</label>
            <select required id="agency" name="agency" class="p-2 w-full order border-[2px] border-gray-300 rounded-[4px] mt-1">
                <c:forEach items="${agencies}" var="agency">
                    <option value="${agency.code}">${agency.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="flex w-full justify-center items-center gap-2">
            <button class="w-full bg-[#01B062] p-4 text-white rounded-md">Ajouter crédit</button>
            <span class="w-full bg-gray-300 p-4 text-black rounded-md text-center cursor-pointer backTo">Revenir</span>
        </div>
    </form>
</div>
</div>
<script src="${pageContext.servletContext.contextPath}/src/script.js" type="text/javascript"></script>
<%@ include file="/WEB-INF/assets/footer.jsp" %>