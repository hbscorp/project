resource "azurerm_resource_group" "rg" {
  name     = var.resource_group_name
  location = var.location
}

resource "azurerm_kubernetes_cluster" "k8s" {
  name                = var.cluster_name
  location            = azurerm_resource_group.rg.location
  resource_group_name = azurerm_resource_group.rg.name
  dns_prefix          = var.dns_prefix
  kubernetes_version  = var.kubernetes_version

  default_node_pool {
    name       = "systempool"
    node_count = var.node_count
    vm_size    = "Standard_D2s_v4"
  }

  identity {
    type = "SystemAssigned"
  }

  network_profile {
    network_plugin = "kubenet"
    network_policy = "calico"
  }

  tags = {
    Environment = "Development"
  }
}

resource "azurerm_container_registry" "acr" {
  name                = var.acr_name
  resource_group_name = azurerm_resource_group.rg.name
  location            = azurerm_resource_group.rg.location
  sku                 = "Standard"
  admin_enabled       = true # false
}

data "azurerm_client_config" "current" {
}

data "azurerm_user_assigned_identity" "identity" {
  name                = "${azurerm_kubernetes_cluster.k8s.name}-agentpool"
  resource_group_name = azurerm_kubernetes_cluster.k8s.node_resource_group
}

resource "azurerm_role_assignment" "role_acrpull" {
  scope                            = azurerm_container_registry.acr.id
  role_definition_name             = "AcrPull"
  principal_id                     = data.azurerm_user_assigned_identity.identity.principal_id
  skip_service_principal_aad_check = true
}
