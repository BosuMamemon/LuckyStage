async function addReply(replyObj){
    const response=await axios.post(`/replies/`, replyObj)
    console.log(response)
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
    const response=await axios.get(`/replies/list/${boardNum}`, {params : {page, size}})
    if(goLast){
        const total = response.data.total
        const lastPage=parseInt(Math.ceil(total/size+1))
        return getList({boardNum:boardNum, page:lastPage, size:size})
    }
    return response.data
}