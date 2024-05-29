import LibroRow from "./LibroRow"

function LibroTable({libros,onEdit,onDelete}){

    return (       
        <table>
            <thead>
                <tr>
                    <th>Título</th>
                    <th>Autor</th>
                    <th>Género</th>
                </tr>
            </thead>
            <tbody>
                {libros.map((libro) =>(
                    <LibroRow key={libro.id} libro={libro} onEdit={onEdit} onDelete={onDelete}/>
                ))}
            </tbody>
        </table>
    )
}
export default LibroTable