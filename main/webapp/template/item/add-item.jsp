<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="../common/header.jsp"></c:import>
</head>
<body>
	<c:import url="../common/nav.jsp"></c:import>

	<!-- container -->
	<div class="container-md">
		<div class="col-md-6 mx-auto">
			<div class="card mb-3">
				<div class="card-header">New Item Form</div>
				<div class="card-body">
					<c:if test="${not empty insertOk }">
						<c:choose>
							<c:when test="${insertOk }">
								<div class="alert alert-success" role="alert">
									Successfully created item.</div>

							</c:when>
							<c:otherwise>
								<div class="alert alert-danger" role="alert">Item creation
									is failed</div>
							</c:otherwise>
						</c:choose>
					</c:if>
					<form action="item" method="post">
						<input type="hidden" name="mode" value="CREATE">
						<div class="mb-3">
							<label for="title" class="form-label">Title</label> <input
								type="text" class="form-control" id="title" name="title"
								required="required">
						</div>

						<div class="mb-3">
							<label for="category" class="form-label">Category</label> <input
								type="text" class="form-control" id="category" name="category"
								required="required">
						</div>

						<div class="mb-3">
							<label for="price" class="form-label">Price</label> <input
								type="text" class="form-control" id="price" name="price"
								required="required">
						</div>

						<div class="mb-3">
							<label for="quantity" class="form-label">Quantity</label> <input
								type="number" class="form-control" id="quantity" name="quantity"
								required="required">
						</div>

						<div class="mb-3">
							<label for="image" class="form-label">Image URL</label> <input
								type="url" class="form-control" id="image" name="image"
								required="required">
						</div>

						<div class="mb-3">
							<label for="description" class="form-label">Description</label>
							<textarea rows="10" class="form-control" id="description"
								name="description" required="required"></textarea>
						</div>

						<div class="mb-3 form-check">
							<input type="checkbox" class="form-check-input" id="essential"
								name="essential" value="true"> <label
								class="form-check-label" for="essential">essential</label>
						</div>
						<button type="submit" class="btn btn-primary float-end">Create</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- form -->


	<c:import url="../common/footer.jsp"></c:import>
</body>
</html>