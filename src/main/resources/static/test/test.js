// 게시글 삭제
            function test() {
                const boardId = 2;
                console.log(boardId);

//                if ( !confirm(boardId + '번 게시글을 삭제할까요?') ) {
//                    return false;
//                }

                const formHtml = `
                    <form id="deleteForm" action="/post/test01" method="post">
                        <input type="hidden" id="boardId" name="boardId" value="${boardId}" />
                    </form>
                `;
                const doc = new DOMParser().parseFromString(formHtml, 'text/html');
                const form = doc.body.firstChild;
                document.body.append(form);
                document.getElementById('deleteForm').submit();
            }