async function addReply(replyObj){
    const response=await axios.post(`/noticesComments/`,replyObj)
    console.log(response)
    return response.data
}
async function getReply(rno){
    const response=await axios.get(`/noticesComments/${rno}`)
    return response.data
}
async function modifyReply(replyObj){
    const response=await axios.put(`/noticesComments/${replyObj.rno}`,replyObj)
    return response.data
}
async function removeReply(rno){
    const response=await axios.delete(`/noticesComments/${rno}`)
    return response.data
}
async function getList({bno, page, size, goLast}){
    console.log("page: ", page)
    let response = await axios.get(`/noticesComments/list/${bno}`, {params : {page, size}})
    if(goLast){
        const total = response.data.total
        const lastPage=parseInt(Math.ceil(total/size))
        // return getList({bno:bno, page:lastPage, size:size})
        response = await axios.get(`/noticesComments/list/${bno}`, {params : {lastPage, size}})
    }
    return response.data
}