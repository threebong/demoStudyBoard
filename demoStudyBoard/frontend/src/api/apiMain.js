import request from './core/api.js'

const joinMember = (formData) => {
    request({
        method: 'post',
        url: '/api/test',
        data: {
            formData
        }
    })
}

export default{
    joinMember
}