async function addReply(replyObj){
    const response=await axios.post(`/replies/`, replyObj)
    console.log("response:");
    console.log(response);
    return response.data
}
async function getReply(boardCommentsNum){
    const response=await axios.get(`/replies/${boardCommentsNum}`)
    return response.data
}
async function modifyReply(replyObj){
    const response=await axios.put(`/replies/${replyObj.boardCommentNum}`,replyObj)
    return response.data
}
async function removeReply(boardCommentsNum){
    const response=await axios.delete(`/replies/${boardCommentsNum}`)
    return response.data
}
async function getList({boardNum, page, size, goLast}){
    console.log("boardNum: ", boardNum)
    console.log("page: ", page)
    let response = await axios.get(`/replies/list/${boardNum}`, {params : {page, size}});
    if(goLast){
        const total = response.data.total
        const lastPage = parseInt(Math.ceil(total/size+1))
        response = await axios.get(`/replies/list/${boardNum}`, {params : {lastPage, size}});
        return response.data
    }
    console.log("response:")
    console.log(response)
    console.log(response.data)
    return response.data
}